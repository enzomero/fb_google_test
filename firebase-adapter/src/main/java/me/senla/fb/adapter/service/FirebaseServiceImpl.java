package me.senla.fb.adapter.service;

import lombok.extern.log4j.Log4j2;
import me.senla.fb.adapter.dto.SingleNotificationDto;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class FirebaseServiceImpl implements FirebaseService {
    @Override
    public SingleNotificationDto push(final SingleNotificationDto singleNotificationDto) {
        log.info(String.format("Push the [%s] to Firebase", singleNotificationDto.toString()));
        //connection to FB api
        log.info(String.format("Push the [%s] to notify db", singleNotificationDto.toString()));
        return singleNotificationDto;
    }
}
