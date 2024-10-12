package com.amigoscode.notification.controller;

import com.amigoscode.notification.payload.NotificationDto;
import com.amigoscode.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/add")
    public void saveNotification(@RequestBody NotificationDto notificationDto){
        log.info("Attempting to add a new notification with ID: {}", notificationDto);
        notificationService.saveNotification(notificationDto);
    }


}
