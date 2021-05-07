package me.senla.api.registration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.senla.api.registration.dto.RegistrationDto;
import me.senla.api.registration.dto.StatRowDto;
import me.senla.api.registration.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/registration/storage")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ObjectMapper objectMapper;

    public RegistrationController(final RegistrationService registrationService, final ObjectMapper objectMapper) {
        this.registrationService = registrationService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/all")
    public List<RegistrationDto> getRegistrations() {
        return registrationService.getRegistrations().stream()
                .map(registration -> objectMapper.convertValue(registration, RegistrationDto.class))
                .collect(Collectors.toList());
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
