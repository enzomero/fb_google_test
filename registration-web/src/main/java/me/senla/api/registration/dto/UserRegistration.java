package me.senla.api.registration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistration {
    private String token;
    private String phone;
    private String appVersion;
}
