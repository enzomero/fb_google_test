package me.senla.api.registration.controller;

import lombok.extern.log4j.Log4j2;
import me.senla.api.registration.dto.RegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import me.senla.api.registration.service.RegistrationService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@Log4j2
@RestController
@RequestMapping("/mobile")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    public ResponseEntity<Object> registration(final @Valid @RequestBody RegistrationDto registrationDto){
        try {
            boolean register = registrationService.register(registrationDto);
            log.info(String.format("Send %s registration", register));
            return ResponseEntity.status(204).body(register);
        } catch (Exception e){
            log.error("Send registration failed");
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/oblivion/{token}")
    public ResponseEntity<Object> deRegistration(@PathParam(value = "token") String token){
        try {
            boolean oblivion = registrationService.oblivion(token);
            log.info(String.format("Send %s deRegistration", oblivion));
            return ResponseEntity.status(204).body(oblivion);
        } catch (Exception e){
            log.error("Send deRegistration failed");
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
