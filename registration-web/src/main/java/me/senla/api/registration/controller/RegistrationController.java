package me.senla.api.registration.controller;

import me.senla.api.registration.dto.RegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import me.senla.api.registration.service.RegistrationService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/mobile")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    public ResponseEntity<Object> registration(final @Valid @RequestBody RegistrationDto registrationDto){
        boolean register = registrationService.register(registrationDto);
        return ResponseEntity.status(register ? 204 : 400).build();
    }

    @PostMapping("/oblivion/{token}")
    public ResponseEntity<Object> deRegistration(@PathParam(value = "token") String token){
        boolean oblivion = registrationService.oblivion(token);
        return ResponseEntity.status(oblivion ? 200 : 400).build();
    }
}
