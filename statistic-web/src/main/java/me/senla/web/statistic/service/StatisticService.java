package me.senla.web.statistic.service;


import me.senla.web.statistic.dto.NotificationRequestDto;
import me.senla.web.statistic.dto.SingleNotificationDto;
import me.senla.web.statistic.dto.StatRowDto;

import java.util.Collection;

public interface StatisticService {
    Collection<StatRowDto> getRegistrations();

    Collection<SingleNotificationDto> getNotifications(NotificationRequestDto phone);
}
