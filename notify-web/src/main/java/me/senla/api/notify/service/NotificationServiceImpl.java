package me.senla.api.notify.service;

import lombok.extern.log4j.Log4j2;
import me.senla.api.notify.dto.NotificationDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class NotificationServiceImpl implements NotificationService {

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

    public NotificationServiceImpl(final KafkaTemplate<String, NotificationDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public boolean sendNotify(final NotificationDto notificationDto) {
        boolean notification = kafkaTemplate.send("notification", notificationDto).isDone();
        log.info(String.format("Notification for [%s] %s", notificationDto.getPhones().toString(), notification ? "in progress" : "failed"));
        return notification;
    }
}
