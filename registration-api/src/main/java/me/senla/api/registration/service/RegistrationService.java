package me.senla.api.registration.service;

import me.senla.api.registration.model.Registration;

import java.util.Set;

public interface RegistrationService {
    Iterable<Registration> getRegistrations();

    Set<String> isRegistered(long phone);
}
