package me.senla.api.notify.controller;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class PushNotifyController {

    private final NotificationService notificationService;

    public PushNotifyController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(value = "/push", consumes = "application/json")
    public ResponseEntity<Object> pushNotify(final @Valid @RequestBody NotificationDto notificationDto){
        try {
            long counter = notificationService.sendNotify(notificationDto);
            log.info(String.format("Send %s notifications", counter));
            return ResponseEntity.status(204).body(counter);
        } catch (Exception e){
            log.error("Send notifications failed");
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
