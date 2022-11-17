package mak.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic")
    public void processMessage(String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List topics,
                               @Header(KafkaHeaders.OFFSET) List offsets) {
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);
    }
}