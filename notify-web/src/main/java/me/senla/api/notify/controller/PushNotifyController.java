package me.senla.api.notify.controller;

import me.senla.api.notify.service.NotificationService;
import me.senla.api.notify.dto.NotificationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mobile")
public class PushNotifyController {

    private final NotificationService notificationService;

    public PushNotifyController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/push", consumes = "application/json")
    public ResponseEntity<Object> pushNotify(final @Valid @RequestBody NotificationDto notificationDto){
        boolean notify = notificationService.sendNotify(notificationDto);
        return ResponseEntity.status(notify ? 204 : 400).build();
    }
}
