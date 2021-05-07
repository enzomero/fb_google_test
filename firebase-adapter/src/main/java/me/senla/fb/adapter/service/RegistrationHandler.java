package me.senla.fb.adapter.service;

import me.senla.fb.adapter.dto.PushNotification;
import me.senla.fb.adapter.dto.SingleNotificationDto;

import java.util.Set;

public interface RegistrationHandler {
    Set<PushNotification> hasDevices(SingleNotificationDto singleNotificationDto);
}
