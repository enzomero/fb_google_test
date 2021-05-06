package me.senla.fb.adapter.dto.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.senla.fb.adapter.dto.SingleNotificationDto;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * This custom specific serializer for Kafka streams
 */
@Log4j2
@NoArgsConstructor
public class SingleNotificationSerializer implements Serializer<SingleNotificationDto> {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(final Map map, final boolean b) {

    }

    @Override
    public byte[] serialize(final String s, final SingleNotificationDto singleNotificationDto) {
        byte[] retVal = null;
        try {
            retVal = objectMapper.writeValueAsString(s).getBytes();
        } catch (Exception e) {
            log.error(String.format("Fail to serialize: [%s]%n%s", singleNotificationDto.toString(), e.getMessage()));
        }
        return retVal;
    }

    @Override
    public void close() {

    }
}
