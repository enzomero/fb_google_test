package me.senla.api.notify.listner;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import me.senla.api.notify.dao.NotificationDao;
import me.senla.api.notify.dto.SingleNotificationDto;
import me.senla.api.notify.model.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Log4j2
@Component
public class NotificationStock {

    private final NotificationDao notificationDao;
    private final ObjectMapper objectMapper;

    public NotificationStock(final NotificationDao notificationDao, final ObjectMapper objectMapper) {
        this.notificationDao = notificationDao;
        this.objectMapper = objectMapper;
    }


    @Transactional
    @KafkaListener(topics = "push", groupId = "test", containerFactory = "cklcfactory")
    public void listener(final SingleNotificationDto singleNotificationDto){
        log.info("Get dto: " + singleNotificationDto);
        Notification notification = objectMapper.convertValue(singleNotificationDto, Notification.class);
        Notification reg = notificationDao.save(notification);
        log.info(String.format("Notification [%s] is done", reg.toString()));
    }
}
