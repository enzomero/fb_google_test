package me.senla.api.registration.service;

import me.senla.api.registration.model.Registration;

public interface RegistrationService {
    Iterable<Registration> getRegistrations();
}
