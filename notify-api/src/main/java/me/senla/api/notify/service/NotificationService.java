package me.senla.api.notify.service;

import me.senla.api.notify.dto.NotificationRequestDto;
import me.senla.api.notify.dao.NotificationDao;
import me.senla.api.notify.model.Notification;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotificationService {
    private final NotificationDao notificationDao;

    public NotificationService(final NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public List<Notification> getNotifications(final NotificationRequestDto notificationRequestDto){
        PageRequest pageRequest = PageRequest.of(notificationRequestDto.getOffset(), notificationRequestDto.getRange());
        Iterable<Notification> byPhone = notificationDao.findByPhone(pageRequest, notificationRequestDto.getPhone());
        return StreamSupport.stream(byPhone.spliterator(), false).collect(Collectors.toList());
    }


}
