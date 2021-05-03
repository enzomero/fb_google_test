package me.senla.api.registration.controller;

import me.senla.api.registration.dto.UserRegistration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import me.senla.api.registration.service.RegistrationService;

@RestController("/mobile")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public String registration(String token, String phone, String appVersion){
        final UserRegistration userRegistration = UserRegistration.builder()
                .token(token)
                .phone(phone)
                .appVersion(appVersion)
                .build();
        return registrationService.register(userRegistration);
    }

    @PostMapping("/oblivion")
    public String deRegistration(String token){
        return registrationService.oblivion(token);
    }
}
