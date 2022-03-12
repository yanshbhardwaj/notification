package com.iedaas.notification.dao;

import com.iedaas.notification.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "select * from notification n join user_notification u on n.notification_uid=u.notification_uid where u.user_uid=?1 order by created_date DESC", nativeQuery = true)
    Page<Notification> getUserNotifications(String userUid, Pageable pageable);
}
