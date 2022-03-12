package com.iedaas.notification.dto;

import java.sql.Timestamp;
import java.util.UUID;

public class NotificationDTO {

    private UUID notificationUid;
    private String notificationType;
    private String actionType;
    private NotificationDataDTO notificationData;
    private Timestamp createdDate;

    public UUID getNotificationUid() {
        return notificationUid;
    }

    public void setNotificationUid(UUID notificationUid) {
        this.notificationUid = notificationUid;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public NotificationDataDTO getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(NotificationDataDTO notificationData) {
        this.notificationData = notificationData;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

}
