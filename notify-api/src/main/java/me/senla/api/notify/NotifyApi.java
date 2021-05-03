package me.senla.api.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class NotifyApi {
    public static void main(String[] args) {
        SpringApplication.run(NotifyApi.class, args);
    }
}
