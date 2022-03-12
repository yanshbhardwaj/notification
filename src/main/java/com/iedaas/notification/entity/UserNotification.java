package com.iedaas.notification.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_notification")
public class UserNotification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_uid")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID userUid;

    @Column(name = "notification_uid")
    @Type(type= "org.hibernate.type.UUIDCharType")
    private UUID notificationUid;

    @Column(name = "delivered")
    private boolean delivered;

    public UserNotification() {
    }

    public UserNotification(UUID userUid, UUID notificationUid, boolean delivered) {
        this.userUid = userUid;
        this.notificationUid = notificationUid;
        this.delivered = delivered;
    }

    public int getId() {
        return id;
    }

    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID userUid) {
        this.userUid = userUid;
    }

    public UUID getNotificationUid() {
        return notificationUid;
    }

    public void setNotificationUid(UUID notificationUid) {
        this.notificationUid = notificationUid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public String toString() {
        return "UserNotification{" +
                "userUid=" + userUid +
                ", notificationUid=" + notificationUid +
                ", delivered=" + delivered +
                '}';
    }
}
