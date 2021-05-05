package me.senla.fb.adapter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleNotificationDto {
    private String title;
    private String text;
    private Long sendTime;
    private String phone;
}