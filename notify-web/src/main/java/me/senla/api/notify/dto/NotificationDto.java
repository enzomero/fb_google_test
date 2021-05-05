package me.senla.api.notify.dto;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;

@Data
@Builder
public class PushNotification {
    private String title;
    private String text;
    private Long sendTime;
    private HashSet<String> strings;

    public PushNotification(final String title, final String text, final Long sendTime, final HashSet<String> strings) {
        this.title = title;
        this.text = text;
        this.sendTime = sendTime;
        this.strings = strings;
    }
}
