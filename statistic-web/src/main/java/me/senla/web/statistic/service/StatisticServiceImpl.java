package me.senla.web.statistic.service;

import me.senla.web.statistic.dto.NotificationRequestDto;
import me.senla.web.statistic.dto.SingleNotificationDto;
import me.senla.web.statistic.dto.StatRowDto;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final RegistrationHandler registrationHandler;
    private final NotificationHandler notificationHandler;

    public StatisticServiceImpl(final RegistrationHandler registrationHandler, final NotificationHandler notificationHandler) {
        this.registrationHandler = registrationHandler;
        this.notificationHandler = notificationHandler;
    }

    @Override
    public Collection<StatRowDto> getRegistrations() {
        return registrationHandler.getRegistrationStatistic();
    }

    @Override
    public Collection<SingleNotificationDto> getNotifications(final NotificationRequestDto notificationRequestDto) {
        return notificationHandler.getNotifications(notificationRequestDto);
    }


}
