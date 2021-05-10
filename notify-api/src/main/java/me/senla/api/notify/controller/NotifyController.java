package me.senla.api.notify.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.senla.api.notify.dto.NotificationRequestDto;
import me.senla.api.notify.dto.SingleNotificationDto;
import me.senla.api.notify.service.NotificationService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/", consumes = "application/json")
    public List<SingleNotificationDto> notificationDtos(final @RequestBody NotificationRequestDto notificationRequestDto){
        return notificationService.getNotifications(notificationRequestDto)
                .stream()
                .map(notification -> objectMapper.convertValue(notification, SingleNotificationDto.class))
                .collect(Collectors.toList());
    }

}
