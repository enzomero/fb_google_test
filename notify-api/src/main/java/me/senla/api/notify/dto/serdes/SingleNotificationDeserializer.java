package me.senla.api.notify.dto.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.senla.api.notify.dto.SingleNotificationDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Log4j2
@NoArgsConstructor
public class SingleNotificationDeserializer implements Deserializer<SingleNotificationDto> {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(final Map map, final boolean b) {

    }

    @Override
    public SingleNotificationDto deserialize(final String s, final byte[] bytes) {
        SingleNotificationDto singleNotificationDto = null;
        try {
            singleNotificationDto = objectMapper.readValue(bytes, SingleNotificationDto.class);
        } catch (Exception e) {
            log.error(String.format("Fail to deserialize: [%s]%n%s", new String(bytes), e.getMessage()));
        }
        return singleNotificationDto;
    }

    @Override
    public void close() {

    }
}
