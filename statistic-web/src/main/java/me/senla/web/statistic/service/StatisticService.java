package me.senla.web.statistic.service;


import java.util.Collection;

public interface StatisticService {
    Collection<StatRowDto> getRegistrations();

    Collection<String> getNotifications(String phone);
}
