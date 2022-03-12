package com.iedaas.notification.controller;

import com.iedaas.notification.AuthorizationFilter;
import com.iedaas.notification.dto.NotificationDTO;
import com.iedaas.notification.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class NotificationController {

    @Autowired
    private AuthorizationFilter authorizationFilter;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification")
    public NotificationDTO addNotification(@RequestBody NotificationDTO notificationDTO){
        NotificationDTO notificationDTODb = notificationService.addChecklistRequestNotification(notificationDTO);
        return notificationDTODb;
    }

    @GetMapping("/notifications")
    public List<NotificationDTO> getUserNotifications(HttpServletRequest request, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        UUID userUid = authorizationFilter.authenticate(request);
        List<NotificationDTO> userNotificationList = notificationService.getUserNotifications(userUid, page, size);
        return userNotificationList;
    }

    @PutMapping("/notification")
    public void updateNotificationStatus(HttpServletRequest request){
        UUID userUid = authorizationFilter.authenticate(request);
        notificationService.updateNotification(userUid);
    }
}
