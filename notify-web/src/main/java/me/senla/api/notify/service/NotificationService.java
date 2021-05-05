package me.senla.api.notify.service;

import me.senla.api.notify.dto.NotificationDto;

public interface NotificationService {
    long sendNotify(NotificationDto notificationDto);
}
