package com.amigoscode.notification.service;

import com.amigoscode.notification.payload.NotificationDto;

public interface NotificationService {

    void saveNotification(NotificationDto notificationDto);
}
