package me.senla.api.registration.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Column(unique = true)
    private String token;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String appVersion;

}
