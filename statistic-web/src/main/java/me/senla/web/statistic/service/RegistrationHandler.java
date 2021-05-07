package me.senla.web.statistic.service;

import me.senla.web.statistic.dto.StatRowDto;

import java.util.List;

public interface RegistrationHandler {
    List<StatRowDto> getRegistrationStatistic();
}
