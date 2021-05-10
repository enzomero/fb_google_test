package me.senla.web.statistic.service;

import lombok.extern.log4j.Log4j2;
import me.senla.web.statistic.dto.StatRowDto;
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

    private static final String PATH = "/registration/storage/uniq";

    @Override
    public Set<StatRowDto> getRegistrationStatistic() {
        String[] strings = hostLink.split(":");
        String host = strings[0];
        String port = strings[1];
        String uri = String.format("http://%s:%s%s", host, port, PATH);
        return subRequest(uri);
    }

    private Set<StatRowDto> subRequest(final String url) {
        try {
            return restTemplate.getForEntity(url, Set.class).getBody();
        }catch (Exception e){
            log.error(String.format("Sub request about registrations statistic was failed,%n" +
                    "%s", e.getMessage()));
            return Collections.emptySet();
        }
    }
}
