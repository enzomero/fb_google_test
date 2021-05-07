package me.senla.api.registration.service;

import me.senla.api.registration.dto.StatRowDto;
import me.senla.api.registration.model.Registration;

import java.util.List;
import java.util.Set;

public interface RegistrationService {
    List<Registration> getRegistrations();

    Set<String> isRegistered(long phone);

    Set<StatRowDto> getVersionsApps();
}
