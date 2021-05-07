package me.senla.api.registration.dto;

public class StatRowDto {
    private String version;
    private Long numberOfRegistration;
    private Long numberOfUniqPhones;

    public String getVersion() {
        return version;
    }

    public StatRowDto setVersion(final String version) {
        this.version = version;
        return this;
    }

    public Long getNumberOfRegistration() {
        return numberOfRegistration;
    }

    public StatRowDto setNumberOfRegistration(final Long numberOfRegistration) {
        this.numberOfRegistration = numberOfRegistration;
        return this;
    }

    public Long getNumberOfUniqPhones() {
        return numberOfUniqPhones;
    }

    public StatRowDto setNumberOfUniqPhones(final Long numberOfUniqPhones) {
        this.numberOfUniqPhones = numberOfUniqPhones;
        return this;
    }
}
