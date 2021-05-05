package me.senla.api.registration.service;

import me.senla.api.registration.dto.RegistrationDto;

public interface RegistrationService {
    boolean register(final RegistrationDto registrationDto);

    boolean oblivion(final String token);
}
