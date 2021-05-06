package me.senla.fb.adapter.service;

import lombok.extern.log4j.Log4j2;
import me.senla.fb.adapter.dto.SingleNotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Log4j2
@Service
public class RegistrationHandlerImpl implements RegistrationHandler {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${sub.host.reg}")
    private String hostLink;

    private static final String PATH_WITH_PARAM = "/registration/storage/%s";

    @Override
    public SingleNotificationDto hasDevices(final SingleNotificationDto singleNotificationDto) {
        if (Objects.isNull(singleNotificationDto)){
            log.warn("Empty message is stream!");
            return null;
        }
        Long phone = singleNotificationDto.getPhone();
        String[] strings = hostLink.split(":");
        String host = strings[0];
        String port = strings[1];
        String uri = String.format("http://%s:%s%s", host, port, String.format(PATH_WITH_PARAM, phone));
        HashSet<String> tokens = subRequest(phone, uri);
        //We ignore that we PROBABLY need to handle all devices for the phone number
        //No BA details to include it to implementation
        if (tokens.size() == 0) {
            log.warn(String.format("Notification for [%s] will be ignored, cause of registration", phone));
            return null;
        }
        return singleNotificationDto;
    }

    private HashSet<String> subRequest(final Long phone, final String url) {
        try {
            return Optional.ofNullable(restTemplate.postForObject(url, phone, HashSet.class))
                    .orElse(new HashSet<String>());
        }catch (Exception e){
            log.error(String.format("Sub request about registration for [%s] was failed%n" +
                    "To url [%s]%n" +
                    "%s", phone, url ,e.getMessage()));
            return new HashSet<String>();
        }
    }
}
