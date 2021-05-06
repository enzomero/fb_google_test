package me.senla.api.notify.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;

@Data
@Builder
public class NotificationDto {
    private String title;
    private String text;
    private long sendTime;
    private HashSet<Long> phones;
}
