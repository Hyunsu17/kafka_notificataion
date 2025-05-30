package com.example.kafka.notification_service.controller;


import com.example.kafka.notification_service.dto.NotificationDto;
import com.example.kafka.notification_service.service.NotificationProducer;
import com.example.kafka.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationProducer notificationProducer;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody NotificationDto dto) {
        notificationProducer.send(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<String>> get(@PathVariable Long userId) {
        List<String> latest = notificationService.getLatest(userId);
        return ResponseEntity.ok(latest);
    }


}
