package me.senla.api.notify.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequestDto {
    private Integer range;
    private Integer offset;
    private String phone;
}
