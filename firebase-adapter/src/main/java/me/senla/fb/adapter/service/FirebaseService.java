package me.senla.fb.adapter.service;

import me.senla.fb.adapter.dto.PushNotification;
import me.senla.fb.adapter.dto.SingleNotificationDto;

public interface FirebaseService {
    SingleNotificationDto push(PushNotification pushNotification);
}
