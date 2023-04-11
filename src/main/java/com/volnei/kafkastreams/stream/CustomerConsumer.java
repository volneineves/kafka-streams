package com.volnei.kafkastreams.stream;

import com.volnei.kafkastreams.enums.KafkaTopicsEnum;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class CustomerConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    // TODO validar com Andre necessidade de receber o nome do topico do Kafka somente parar log
    public <T> Consumer<KStream<String, T>> consume(KafkaTopicsEnum topic, Consumer<T> service) {

        return input -> input.foreach((key, value) -> {
            try {
                service.accept(value); // executa o método contido no service
                LOGGER.info("Receiving message from Kafka topic {}", topic.name);
            } catch (Exception e) {
                LOGGER.error("Error while receiving message from Kafka topic {}: {}", topic.name, e.getMessage());
            }
        });
    }
}
