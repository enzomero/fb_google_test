package me.senla.fb.adapter.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import me.senla.fb.adapter.dto.SingleNotificationDto;
import me.senla.fb.adapter.service.FirebaseService;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
@Configuration
public class KafkaConfig {

    public static final String T_INPUT = "notification";
    public static final String T_OUTPUT = "push";
    @Value("${sub.host.reg}")
    private String host;
    private static final String PATH_WITH_PARAM = "/registration/storage/%s";
    private static final RestTemplate restTemplate = new RestTemplate();
    private final FirebaseService firebaseService;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    public KafkaConfig(final FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @Bean
    public KStream<String, SingleNotificationDto> kStream(@Autowired StreamsBuilder kStreamBuilder) {
        Serde<SingleNotificationDto> serdeFrom = Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(SingleNotificationDto.class));
        KStream<String, SingleNotificationDto> stream = kStreamBuilder
                .stream(T_INPUT, Consumed.with(Serdes.String(), Serdes.String()))
                .mapValues(this::getUserFromString)
//                .mapValues(this::isRegistered)
                .filter((s, singleNotificationDto) -> Objects.nonNull(singleNotificationDto))
                .mapValues(firebaseService::push);
        stream.to(T_OUTPUT, Produced.with(Serdes.String(), serdeFrom));
        return stream;
    }

    @Bean
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "fire_base_adapter");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public StreamsBuilder streamsBuilder()
    {
        return new StreamsBuilder();
    }

    private SingleNotificationDto isRegistered(final SingleNotificationDto singleNotificationDto) {
        final String url = UriComponentsBuilder.newInstance()
                .host(host)
                .path(String.format(PATH_WITH_PARAM, singleNotificationDto.getPhone()))
                .toUriString();
        Boolean isRegistered =
                restTemplate.postForObject(url, singleNotificationDto.getPhone(), Boolean.class);
        if (Objects.isNull(isRegistered) || !isRegistered){
            return null;
        }
        return singleNotificationDto;
    }

    SingleNotificationDto getUserFromString(String userString) {
        SingleNotificationDto user = null;
        try {
            user = objectMapper().readValue(userString, SingleNotificationDto.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return user;
    }
}
