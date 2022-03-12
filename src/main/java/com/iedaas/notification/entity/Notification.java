package com.iedaas.notification.entity;

import com.iedaas.notification.dto.NotificationDataDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @Column(name = "notification_uid")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID notificationUid = UUID.randomUUID();

    @Column(name = "notification_type")
    private String notificationType;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "notification_data")
    private String notificationData;

    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;

    public Notification() {
    }

    public Notification(UUID notificationUid, String notificationType, String actionType,
                        String notificationData, Timestamp createdDate) {
        this.notificationUid = notificationUid;
        this.notificationType = notificationType;
        this.actionType = actionType;
        this.notificationData = notificationData;
        this.createdDate = createdDate;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public UUID getNotificationUid() {
        return notificationUid;
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

    public String getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(String notificationData) {
        this.notificationData = notificationData;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }


    @Override
    public String toString() {
        return "Notification{" +
                "notificationUid=" + notificationUid +
                ", notificationType='" + notificationType + '\'' +
                ", actionType='" + actionType + '\'' +
                ", notificationData='" + notificationData + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return notificationUid.equals(that.notificationUid) && notificationType.equals(that.notificationType) && actionType.equals(that.actionType) && notificationData.equals(that.notificationData) && createdDate.equals(that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationUid, notificationType, actionType, notificationData, createdDate);
    }
}
