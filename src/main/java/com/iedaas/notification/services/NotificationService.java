package com.iedaas.notification.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iedaas.notification.dao.CustomRepository;
import com.iedaas.notification.dao.NotificationRepository;
import com.iedaas.notification.dao.UserNotificationRepository;
import com.iedaas.notification.dto.NotificationDataDTO;
import com.iedaas.notification.dto.NotificationDTO;
import com.iedaas.notification.entity.Notification;
import com.iedaas.notification.entity.UserNotification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @Autowired
    private CustomRepository customRepository;

    @Autowired
    private ModelMapper modelMapper;

    public NotificationDTO addChecklistRequestNotification(NotificationDTO notificationDTO){
        Notification userTypeNotification = modelMapper.map(notificationDTO, Notification.class);
        userTypeNotification.setNotificationType("user");
        Notification entityNotification = modelMapper.map(notificationDTO, Notification.class);
        entityNotification.setNotificationType("checklistRequest");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userTypeNotification.setNotificationData(objectMapper.writeValueAsString(notificationDTO.getNotificationData()));
            entityNotification.setNotificationData(objectMapper.writeValueAsString(notificationDTO.getNotificationData()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Set<UUID> followers = new HashSet<>();
        List<UUID> entityFollowers = new ArrayList<>();
        Set<UUID> userFollowers = new HashSet<>();
        NotificationDataDTO notificationDataDTO = notificationDTO.getNotificationData();
        if(notificationDataDTO.getChecklistUid()==null & notificationDTO.getNotificationType().equals("checklistRequestAdded")){
            userFollowers.addAll(customRepository.getUserFollowersList(notificationDataDTO.getUserDTO().getUserUid()));
        }
        else if(notificationDataDTO.getChecklistUid()==null & notificationDTO.getNotificationType().equals("checklistRequestUpdated")){
            notificationRepository.save(entityNotification);
            entityFollowers = customRepository.getChecklistRequestFollowersList(notificationDataDTO.getChecklistRequestNotificationDTO().getChecklistRequestUid());
            userFollowers.addAll(customRepository.getUserFollowersList(notificationDataDTO.getUserDTO().getUserUid()));
        }
        else if(notificationDataDTO.getChecklistUid()!=null & notificationDTO.getNotificationType().equals("checklistAdded")){
            notificationRepository.save(entityNotification);
            entityFollowers = customRepository.getChecklistRequestFollowersList(notificationDataDTO.getChecklistRequestNotificationDTO().getChecklistRequestUid());
            userFollowers.addAll(customRepository.getUserFollowersList(notificationDataDTO.getUserDTO().getUserUid()));
        }
        Notification userNotificationDb = notificationRepository.save(userTypeNotification);
        List<UserNotification> userNotificationList = new ArrayList<>();
        followers.addAll(userFollowers);
        followers.addAll(entityFollowers);
        for(UUID userUid : followers){
            UserNotification userNotification;
            if(userFollowers.contains(userUid)) {
                userNotification = new UserNotification(userUid, userTypeNotification.getNotificationUid(), false);
            }
            else{
                userNotification = new UserNotification(userUid, entityNotification.getNotificationUid(), false);
            }
            userNotificationList.add(userNotification);
        }
        userNotificationRepository.saveAll(userNotificationList);
        NotificationDTO notificationDTODb = modelMapper.map(userNotificationDb, NotificationDTO.class);
        return notificationDTODb;
    }

    public List<NotificationDTO> getUserNotifications(UUID userUid, Optional<Integer> page, Optional<Integer> size){
        Page<Notification> result = notificationRepository.getUserNotifications(String.valueOf(userUid), PageRequest.of(page.orElse(0), size.orElse(5)));
        List<Notification> notifications = result.getContent();
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for(Notification notification : notifications){
            NotificationDTO notificationDTO = modelMapper.map(notification, NotificationDTO.class);
            notificationDTOList.add(notificationDTO);
        }
        return notificationDTOList;
    }

    public void updateNotification(UUID userUid){
        List<UserNotification> userNotificationList = userNotificationRepository.getUserNotifications(String.valueOf(userUid));
        for(UserNotification userNotification : userNotificationList){
            userNotification.setDelivered(true);
            userNotificationRepository.save(userNotification);
        }
    }
}
