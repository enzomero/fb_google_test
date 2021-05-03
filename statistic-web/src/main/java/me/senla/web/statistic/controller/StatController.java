package me.senla.web.statistic.controller;

import me.senla.web.statistic.service.StatisticService;
import org.springframework.stereotype.Controller;

import java.util.Collection;

//Need web site to show it
@Controller
public class StatController {
    private final StatisticService statisticService;

    public StatController(final StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    public Collection<StatRowDto> getAllRegistrations(){
        return statisticService.getRegistrations();
    }

    public Collection<String> getAllNotify(String phone){
        return statisticService.getNotifications(phone);
    }
}
