package me.senla.web.statistic.service;

import lombok.extern.log4j.Log4j2;
import me.senla.web.statistic.dto.NotificationRequestDto;
import me.senla.web.statistic.dto.SingleNotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class NotificationHandlerImpl implements NotificationHandler {
    private static final RestTemplate restTemplate = new RestTemplate();

    @Value("${sub.host.ntf}")
    private String hostLink;

    private static final String PATH = "/notifications/storage/";

    @Override
    public List<SingleNotificationDto> getNotifications(final NotificationRequestDto notificationRequestDto) {
        String[] strings = hostLink.split(":");
        String host = strings[0];
        String port = strings[1];
        String uri = String.format("http://%s:%s%s", host, port, PATH);
        return subRequest(uri, notificationRequestDto);
    }
    private List<SingleNotificationDto> subRequest(final String url, final NotificationRequestDto notificationRequestDto) {
        RequestEntity<NotificationRequestDto> requestEntity = RequestEntity.put(URI.create(url)).body(notificationRequestDto);
        try {
            return Optional.ofNullable(restTemplate.exchange(requestEntity, List.class))
                    .map(HttpEntity::getBody)
                    .orElse(Collections.emptyList());
        }catch (Exception e){
            log.error(String.format("Sub request about notifications for [%s] was failed,%n" +
                    "%s", notificationRequestDto, e.getMessage()));
            return Collections.emptyList();
        }
    }

}
