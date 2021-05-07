package me.senla.fb.adapter.config;

import lombok.extern.log4j.Log4j2;
import me.senla.fb.adapter.dto.SingleNotificationDto;
import me.senla.fb.adapter.dto.serdes.SingleNotificationDeserializer;
import me.senla.fb.adapter.dto.serdes.SingleNotificationSerializer;
import me.senla.fb.adapter.service.FirebaseService;
import me.senla.fb.adapter.service.RegistrationHandler;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.Properties;

@Log4j2
@Configuration
public class KafkaConfig {

    public static final String T_INPUT = "notification";
    public static final String T_OUTPUT = "push";
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;
    private final FirebaseService firebaseService;
    private final RegistrationHandler registrationHandler;

    public KafkaConfig(final FirebaseService firebaseService, final RegistrationHandler registrationHandler) {
        this.firebaseService = firebaseService;
        this.registrationHandler = registrationHandler;
    }

    @Bean
    public Properties properties() {
        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "fb-test");
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return streamProperties;
    }

    @Bean
    public void topology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, SingleNotificationDto> stream = streamsBuilder
                .stream(T_INPUT, Consumed.with(Serdes.String(),
                        Serdes.serdeFrom(new SingleNotificationSerializer(), new SingleNotificationDeserializer())
                ));

        KStream<String, SingleNotificationDto> peek = stream
                .mapValues(registrationHandler::hasDevices)
                .filter((key, value) -> !value.isEmpty())//ignore fails
                .flatMapValues(value -> value)
                .mapValues(firebaseService::push)
                .filter((key, value) -> Objects.nonNull(value))//ignore fails
                .peek((key, value) -> log.info(String.format("STREAM[K:%s|V:%S]", key, value)));

        peek.to(T_OUTPUT, Produced.with(Serdes.String(),
                Serdes.serdeFrom(new SingleNotificationSerializer(), new SingleNotificationDeserializer())
        ));
        new KafkaStreams(streamsBuilder.build(), properties()).start();
    }

    @Bean
    public StreamsBuilder streamsBuilder() {
        return new StreamsBuilder();
    }

}
