package me.senla.web.statistic.service;

import me.senla.web.statistic.dto.NotificationRequestDto;
import me.senla.web.statistic.dto.SingleNotificationDto;

import java.util.List;

public interface NotificationHandler {
    List<SingleNotificationDto> getNotifications(NotificationRequestDto notificationRequestDto);
}
