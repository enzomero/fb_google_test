package me.senla.api.registration.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class RegistrationDto {
    @NotBlank
    private String token;
    @NotNull
    private Integer phone;
    @NotBlank
    private String appVersion;
}
