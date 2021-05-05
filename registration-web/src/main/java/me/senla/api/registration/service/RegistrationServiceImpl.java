package me.senla.api.registration.service;

import lombok.extern.log4j.Log4j2;
import me.senla.api.registration.dto.RegistrationDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final KafkaTemplate<String, RegistrationDto> kafkaTemplate;

    public RegistrationServiceImpl(final KafkaTemplate<String, RegistrationDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public boolean register(final RegistrationDto registrationDto) {
        boolean registration = kafkaTemplate.send("registration", registrationDto).isDone();
        log.info(String.format("Registration of user [%s] %s", registrationDto.getPhone(), registration ? "started": "failed"));
        return registration;
    }

    @Override
    public boolean oblivion(final String token) {
        RegistrationDto obligateRegistration = RegistrationDto.builder()
                .token(token)
                .build();
        boolean registration = kafkaTemplate.send("registration", obligateRegistration).isDone();
        log.info(String.format("De-registration of user %s", registration ? "started": "failed"));
        return registration;
    }
}
