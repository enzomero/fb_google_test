package me.senla.api.notify.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.senla.api.notify.dto.NotificationDto;
import me.senla.api.notify.dto.NotificationRequestDto;
import me.senla.api.notify.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notifications/storage")
public class NotifyController {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    public NotifyController(final NotificationService notificationService, final ObjectMapper objectMapper) {
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{phone}")
    public List<NotificationDto> notificationDtos(final @RequestBody NotificationRequestDto notificationRequestDto){
        return notificationService.getNotifications(notificationRequestDto)
                .stream()
                .map(notification -> objectMapper.convertValue(notification, NotificationDto.class))
                .collect(Collectors.toList());
    }

}
