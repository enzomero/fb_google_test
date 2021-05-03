package me.senla.fb.adapter.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistration {
    private TokenFb token;
    private String phone;
    private String appVersion;

    public UserRegistration(final TokenFb token, final String phone, final String appVersion) {
        this.token = token;
        this.phone = phone;
        this.appVersion = appVersion;
    }
}
