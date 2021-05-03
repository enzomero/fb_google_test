package me.senla.api.registration.service;

import me.senla.api.registration.dto.UserRegistration;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Override
    public String register(final UserRegistration userRegistration) {
        //sent to kafka registration data
        return "null";
    }

    @Override
    public String oblivion(final String token) {
        //sent to kafka de-reregistration data
        return "null";
    }
}
