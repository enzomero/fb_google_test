package me.senla.web.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleNotificationDto {
    private String title;
    private String text;
    private long sendTime;
    private long phone;
}
