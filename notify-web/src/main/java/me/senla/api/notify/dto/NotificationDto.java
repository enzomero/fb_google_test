package me.senla.api.notify.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.HashSet;

@Data
@Builder
public class NotificationDto {
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotNull
    private Long sendTime;
    @Size(min = 1)
    private HashSet<String> phones;

    public NotificationDto(final String title, final String text, final Long sendTime, final HashSet<String> phones) {
        this.title = title;
        this.text = text;
        this.sendTime = sendTime;
        this.phones = phones;
    }
}
