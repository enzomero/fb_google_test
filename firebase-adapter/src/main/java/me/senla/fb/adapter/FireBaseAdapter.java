package me.senla.fb.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class FireBaseAdapter {
    public static void main(String[] args) {
        SpringApplication.run(FireBaseAdapter.class, args);
    }
}
