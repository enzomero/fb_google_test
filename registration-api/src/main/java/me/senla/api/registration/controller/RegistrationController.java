package me.senla.api.registration.controller;

import me.senla.api.registration.dto.StatRowDto;
import me.senla.api.registration.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/registration/storage")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/{phone}", produces ="application/json")
    public Set<String> isRegistered(final @PathVariable(value = "phone") long phone) {
        return registrationService.isRegistered(phone);
    }

    @GetMapping(value = "/uniq", produces ="application/json")
    public Set<StatRowDto> isRegistered() {
        return registrationService.getVersionsApps();
    }

}
