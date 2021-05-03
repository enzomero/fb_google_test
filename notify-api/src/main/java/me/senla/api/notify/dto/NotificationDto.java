package me.senla.api.notify.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class NotificationDto {
    private String title;
    private String text;
    private Long sendTime;
    private HashSet<String> strings;

    public NotificationDto(final String title, final String text, final Long sendTime, final Set<String> strings) {
        this.title = title;
        this.text = text;
        this.sendTime = sendTime;
        this.strings = new HashSet<>(strings);
    }
}
