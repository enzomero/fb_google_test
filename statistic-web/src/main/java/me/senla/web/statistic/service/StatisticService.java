package me.senla.web.statistic.service;


import me.senla.web.statistic.dto.StatRowDto;

import java.util.Collection;

public interface StatisticService {
    Collection<StatRowDto> getRegistrations();

    Collection<String> getNotifications(String phone);
}
