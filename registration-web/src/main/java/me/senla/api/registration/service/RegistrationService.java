package me.senla.api.registration.service;

import me.senla.api.registration.dto.UserRegistration;

public interface RegistrationService {
    String register(final UserRegistration userRegistration);

    String oblivion(final String token);
}
