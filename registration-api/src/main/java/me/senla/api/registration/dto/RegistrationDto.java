package me.senla.api.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private long id;
    private String token;
    private long phone;
    private String appVersion;
}
