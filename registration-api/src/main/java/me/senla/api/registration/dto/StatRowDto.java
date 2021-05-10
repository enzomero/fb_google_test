package me.senla.api.registration.dto;

import lombok.Getter;

@Getter
public class StatRowDto {
    private String version;
    private Long numberOfRegistration;
    private Long numberOfUniqPhones;

    public StatRowDto setVersion(final String version) {
        this.version = version;
        return this;
    }

    public StatRowDto setNumberOfRegistration(final Long numberOfRegistration) {
        this.numberOfRegistration = numberOfRegistration;
        return this;
    }

    public StatRowDto setNumberOfUniqPhones(final Long numberOfUniqPhones) {
        this.numberOfUniqPhones = numberOfUniqPhones;
        return this;
    }
}
