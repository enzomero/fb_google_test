package me.senla.fb.adapter.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.log4j.Log4j2;
import me.senla.fb.adapter.dto.PushNotification;
import me.senla.fb.adapter.dto.SingleNotificationDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Log4j2
@Service
public class FirebaseServiceImpl implements FirebaseService {

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseServiceImpl(final FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    @Override
    public SingleNotificationDto push(final PushNotification pushNotification) {
        SingleNotificationDto dto = pushNotification.getNotificationDto();
        log.info(String.format("Push the [%s] to Firebase", dto.toString()));
        Notification notification = Notification
                .builder()
                .setTitle(dto.getTitle())
                .setBody(dto.getText())
                .build();

        Message message = Message
                .builder()
                .setToken(pushNotification.getToken())
                .putAllData(
                        Map.of("phone", String.valueOf(dto.getPhone()),
                                "sendTime",String.valueOf(dto.getSendTime())))
                .setNotification(notification)
                .build();
        try {
            firebaseMessaging.send(message);
            log.info(String.format("Push the [%s] to notify db", dto.toString()));
            return dto;
        } catch (Exception e){
            log.error(String.format("Firebase rejects request: %s", e.getMessage()));
            return null;
        }
    }
}
