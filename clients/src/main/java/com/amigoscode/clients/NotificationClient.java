package com.amigoscode.clients;

import com.amigoscode.clients.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("NOTIFICATION")
public interface NotificationClient {

    @PostMapping(path = "/api/v1/notification/add")
    void saveNotification(@RequestBody NotificationDto notificationDto);
}
