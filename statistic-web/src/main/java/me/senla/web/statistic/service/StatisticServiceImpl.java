package me.senla.web.statistic.service;

import me.senla.web.statistic.dto.StatRowDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Override
    public Collection<StatRowDto> getRegistrations() {
        //ask reg db
        //filter
        return Collections.emptyList();
    }

    @Override
    public Collection<String> getNotifications(final String phone) {
        //ask msg db by phone
        //map
        return Collections.emptyList();
    }
}
