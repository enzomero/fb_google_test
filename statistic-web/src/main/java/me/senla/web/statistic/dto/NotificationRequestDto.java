package me.senla.web.statistic.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class NotificationRequestDto {
    private Integer range;
    private Integer offset;
    @NotBlank
    private String phone;
}
