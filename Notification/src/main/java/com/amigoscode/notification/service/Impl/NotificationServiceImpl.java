package com.amigoscode.notification.service.Impl;

import com.amigoscode.notification.entity.Notification;
import com.amigoscode.notification.payload.NotificationDto;
import com.amigoscode.notification.repository.NotificationRepository;
import com.amigoscode.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.mutation.group.PreparedStatementGroup;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

      private final NotificationRepository notificationRepository;
      private final ModelMapper mapper;
    @Override
    public void saveNotification(NotificationDto notificationDto) {
        Notification notification = mapper.map(notificationDto,Notification.class);
        log.info("Saving Notification Information  whit ID: {}",notification.getNotificationId() );

        notificationRepository.save(notification);

    }
}
