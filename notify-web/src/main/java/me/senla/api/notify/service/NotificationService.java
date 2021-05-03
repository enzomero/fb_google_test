package me.senla.api.notify.service;

import me.senla.api.notify.dto.PushNotification;

public interface NotificationService {
    String sendNotify(PushNotification pushNotification);
}
