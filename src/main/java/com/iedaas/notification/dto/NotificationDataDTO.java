package com.iedaas.notification.dto;

import java.util.UUID;

public class NotificationDataDTO {

    private ChecklistRequestNotificationDTO checklistRequestNotificationDTO;
    private UUID checklistUid;
    private UserDTO userDTO;

    public ChecklistRequestNotificationDTO getChecklistRequestNotificationDTO() {
        return checklistRequestNotificationDTO;
    }

    public void setChecklistRequestNotificationDTO(ChecklistRequestNotificationDTO checklistRequestNotificationDTO) {
        this.checklistRequestNotificationDTO = checklistRequestNotificationDTO;
    }

    public UUID getChecklistUid() {
        return checklistUid;
    }

    public void setChecklistUid(UUID checklistUid) {
        this.checklistUid = checklistUid;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
