package com.example.kafka.notification_service.service;

import com.example.kafka.notification_service.dto.NotificationDto;
import com.example.kafka.notification_service.model.Notification;
import com.example.kafka.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final RedisTemplate<String, String> redis;
    private final NotificationRepository repository;

    @KafkaListener(topics ="notificatitons", groupId = "notification-group")
    public void consume(NotificationDto dto){

        // 1) MySQL 저장
        Notification notification = Notification.builder()
                .userId(dto.getUserId())
                .message(dto.getMessage())
                .createdAt(LocalDateTime.now())
                .build();
        repository.save(notification);

        // 2) Redis 캐시 (List 최대 10개)
        String key ="notification:" + dto.getUserId();
        redis.opsForList().leftPush(key, dto.getMessage());
        redis.opsForList().trim(key,0,9);
    }




}
