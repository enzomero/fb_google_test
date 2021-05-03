package me.senla.api.notify.service;

import me.senla.api.notify.dto.PushNotification;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public String sendNotify(final PushNotification pushNotification) {
        //ask Registration if exist user
        //send to kafka
        return null;
    }
}
