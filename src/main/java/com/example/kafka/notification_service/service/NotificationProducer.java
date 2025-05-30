package com.example.kafka.notification_service.service;

import com.example.kafka.notification_service.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

    public void send(NotificationDto dto){
        kafkaTemplate.send("notifications", dto);
    }
}
