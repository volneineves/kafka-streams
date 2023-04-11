package com.volnei.kafkastreams.stream;

import com.volnei.kafkastreams.enums.KafkaTopicsEnum;
import com.volnei.kafkastreams.model.util.Identifiable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerPublisher {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate<String, ?> kafkaTemplate;

    @Autowired
    public CustomerPublisher(KafkaTemplate<String, ?> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public <T extends Identifiable> void publish(KafkaTopicsEnum topic, T payload) {
        Message<T> message = MessageBuilder.withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic.name)
                .setHeader(KafkaHeaders.KEY, payload.getId().toString().getBytes()).build();

        try {
            kafkaTemplate.send(message);
            LOGGER.info("Sending message to Kafka topic: {}", topic.name);
        } catch (Exception e) {
            LOGGER.error("Error while sending message to stream topic {}: {}", topic.name, e.getMessage());
        }
    }
}
