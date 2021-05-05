package me.senla.api.registration.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class RegistrationDto {
    @NotBlank
    private String token;
    @NotEmpty
    @Size(min = 8, max = 12)
    private Integer phone;
    @NotBlank
    private String appVersion;
}
