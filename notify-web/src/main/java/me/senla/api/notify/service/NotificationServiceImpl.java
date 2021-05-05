package me.senla.api.notify.service;

import lombok.extern.log4j.Log4j2;
import me.senla.api.notify.dto.NotificationDto;
import me.senla.api.notify.dto.SingleNotificationDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
public class NotificationServiceImpl implements NotificationService {

    private final KafkaTemplate<String, SingleNotificationDto> kafkaTemplate;

    public NotificationServiceImpl(final KafkaTemplate<String, SingleNotificationDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public long sendNotify(final NotificationDto notificationDto) {
        List<SingleNotificationDto> collect = notificationDto.getPhones().stream()
                .map(s -> getSingleNotificationDto(notificationDto, s))
                .collect(Collectors.toList());
        collect.forEach(this::getSend);
        return collect.size();
    }

    private SingleNotificationDto getSingleNotificationDto(final NotificationDto notificationDto, final String s) {
        return SingleNotificationDto.builder()
                .sendTime(notificationDto.getSendTime())
                .title(notificationDto.getTitle())
                .text(notificationDto.getText())
                .phone(s)
                .build();
    }

    private boolean getSend(final SingleNotificationDto singleNotificationDto) {
        boolean notification = kafkaTemplate.send("notification", UUID.randomUUID().toString(),singleNotificationDto).isDone();
        log.info(String.format("Notification for [%s] in progress", singleNotificationDto.getPhone()));
        return notification;
    }
}
