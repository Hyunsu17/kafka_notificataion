package com.example.kafka.notification_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final RedisTemplate<String, String> redis;

    public List<String> getLatest(Long userId){
        String key = "notifications:"+ userId;
        return redis.opsForList().range(key, 0, 9);
    }
}
