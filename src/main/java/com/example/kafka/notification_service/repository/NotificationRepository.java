package com.example.kafka.notification_service.repository;

import com.example.kafka.notification_service.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findTop10ByUserIdOrderByCreatedAtDesc(Long userId);
}
