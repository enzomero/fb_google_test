package me.senla.api.notify.controller;

import me.senla.api.notify.service.NotificationService;
import me.senla.api.notify.dto.PushNotification;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@RestController
public class PushNotifyController {

    private final NotificationService notificationService;

    public PushNotifyController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public String pushNotify(String title, String text, Long sendTime, String... phones){
        PushNotification pushNotification = new PushNotification(title, text, sendTime, new HashSet<String>(Arrays.asList(phones)));

        notificationService.sendNotify(pushNotification);
        return "";
    }
}
