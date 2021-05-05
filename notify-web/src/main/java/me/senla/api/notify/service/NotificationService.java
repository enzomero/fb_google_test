package me.senla.api.notify.service;

import me.senla.api.notify.dto.NotificationDto;

public interface NotificationService {
    boolean sendNotify(NotificationDto notificationDto);
}
