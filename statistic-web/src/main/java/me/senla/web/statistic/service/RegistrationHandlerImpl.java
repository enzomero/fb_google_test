package me.senla.web.statistic.service;

import lombok.extern.log4j.Log4j2;
import me.senla.web.statistic.dto.NotificationRequestDto;
import me.senla.web.statistic.dto.StatRowDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class RegistrationHandlerImpl implements RegistrationHandler {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${sub.host.reg}")
    private String hostLink;

    private static final String PATH_WITH_PARAM = "/registration/storage/all";

    @Override
    public List<StatRowDto> getRegistrationStatistic() {
        return null;
    }

    @Override
    public Set<S> hasDevices() {
        if (Objects.isNull(singleNotificationDto)){
            log.warn("Empty message is stream!");
            return new HashSet<>();
        }
        Long phone = singleNotificationDto.getPhone();
        String[] strings = hostLink.split(":");
        String host = strings[0];
        String port = strings[1];
        String uri = String.format("http://%s:%s%s", host, port, String.format(PATH_WITH_PARAM, phone));
        return subRequest(phone, uri)
                .stream()
                .map(s -> PushNotification.builder()
                .token(s)
                .notificationDto(singleNotificationDto)
                .build())
                .collect(Collectors.toSet());
    }

    private List<NotificationRequestDto> subRequest(final Long phone, final String url) {
        try {
            return Optional.ofNullable(restTemplate.getForEntity(url, List.class))
                    .orElse(Collections.emptyList());
        }catch (Exception e){
            log.error(String.format("Sub request about registration for [%s] was failed,%n" +
                    "%s", phone ,e.getMessage()));
            return Collections.emptySet();
        }
    }
}
