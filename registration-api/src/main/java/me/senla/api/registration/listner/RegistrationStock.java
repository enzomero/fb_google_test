package me.senla.api.registration.listner;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import me.senla.api.registration.dao.RegistrationDao;
import me.senla.api.registration.dto.RegistrationDto;
import me.senla.api.registration.model.Registration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Log4j2
@Component
public class RegistrationStock {

    private final RegistrationDao registrationDao;
    private final ObjectMapper objectMapper;

    public RegistrationStock(final RegistrationDao registrationDao, final ObjectMapper objectMapper) {
        this.registrationDao = registrationDao;
        this.objectMapper = objectMapper;
    }

    @Transactional
    @KafkaListener(topics = "registration", groupId = "test", containerFactory = "cklcfactory")
    public void listener(final RegistrationDto registrationDto){
        log.info("Get dto: " + registrationDto.toString());
        Registration registration = objectMapper.convertValue(registrationDto, Registration.class);
        if (isNull(registrationDto.getPhone()) &&
                isNull(registrationDto.getAppVersion()) &&
                nonNull(registrationDto.getToken())
        ){
            String token = registrationDto.getToken();
            registrationDao.removeByToken(token);
            log.warn("Remove token: " + token + " from db");
            return;
        }
        Registration reg = registrationDao.save(registration);
        log.info(String.format("Registration [%s] is done", reg.toString()));
    }
}
