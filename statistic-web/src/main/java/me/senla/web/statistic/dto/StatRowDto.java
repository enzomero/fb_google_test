package me.senla.web.statistic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatRowDto {
    private String version;
    private Long numberOfRegistration;
    private Long numberOfUniqPhones;
}
