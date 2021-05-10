package me.senla.web.statistic.service;

import me.senla.web.statistic.dto.StatRowDto;

import java.util.Set;

public interface RegistrationHandler {
    Set<StatRowDto> getRegistrationStatistic();
}
