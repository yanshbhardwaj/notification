package com.iedaas.notification.dao;

import com.iedaas.notification.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Integer> {

    @Query(value = "select * from user_notification u where u.user_uid=?1 and n.notification_uid=?2", nativeQuery = true)
    UserNotification getUserNotification(String userUid, String notificationUid);

    @Query(value = "select * from user_notification u join notification n on u.notification_uid=n.notification_uid where u.user_uid=?1 and u.delivered='false' and n.created_date < CURRENT_TIMESTAMP", nativeQuery = true)
    List<UserNotification> getUserNotifications(String userUid);
}
