package me.senla.api.registration.service;

import me.senla.api.registration.dto.RegistrationDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final KafkaTemplate<String, RegistrationDto> kafkaTemplate;

    public RegistrationServiceImpl(final KafkaTemplate<String, RegistrationDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public boolean register(final RegistrationDto registrationDto) {
        return kafkaTemplate.send("registration", registrationDto).isDone();
    }

    @Override
    public boolean oblivion(final String token) {
        RegistrationDto obligateRegistration = RegistrationDto.builder()
                .token(token)
                .build();
        return kafkaTemplate.send("registration", obligateRegistration).isDone();
    }
}
