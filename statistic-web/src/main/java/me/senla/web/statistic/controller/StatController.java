package me.senla.web.statistic.controller;

import me.senla.web.statistic.dto.NotificationRequestDto;
import me.senla.web.statistic.dto.SingleNotificationDto;
import me.senla.web.statistic.dto.StatRowDto;
import me.senla.web.statistic.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.Collection;

//Need web site to show it
@Controller
public class StatController {
    private final StatisticService statisticService;

    public StatController(final StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("eventName", "FIFA 2018");
        return "index";
    }

    @GetMapping("/reg/stats")
    public String getAllRegistrations(ModelMap modelMap){
        Collection<StatRowDto> registrations = statisticService.getRegistrations();
        modelMap.put("registrations", registrations);
        return "reg/stats";
    }

    @GetMapping("/ntf/find")
    public String getAllNotify(final @Valid @RequestParam("phone") String phone,
                                                          final @RequestParam(value = "range", defaultValue = "20") Integer range,
                                                          final @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                               ModelMap modelMap){
        NotificationRequestDto requestDto = NotificationRequestDto.builder()
                .offset(offset)
                .range(range)
                .phone(phone)
                .build();
        Collection<SingleNotificationDto> notifications = statisticService.getNotifications(requestDto);
        modelMap.put("notifications", notifications);
        return "ntf/notify";
    }
}
