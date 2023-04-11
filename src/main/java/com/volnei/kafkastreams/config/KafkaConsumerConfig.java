package com.volnei.kafkastreams.config;

import com.volnei.kafkastreams.model.entity.Customer;
import com.volnei.kafkastreams.service.CustomerService;
import com.volnei.kafkastreams.stream.CustomerConsumer;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

import static com.volnei.kafkastreams.enums.KafkaTopicsEnum.SAVE_PERSON;

@Configuration
public class KafkaConsumerConfig {

    // TODO validar com Andre se é melhor usar o service ou o repository para persistir
    private final CustomerService customerService;
    private final CustomerConsumer customerConsumer;

    @Autowired
    public KafkaConsumerConfig(CustomerService customerService, CustomerConsumer customerConsumer) {
        this.customerService = customerService;
        this.customerConsumer = customerConsumer;
    }

    @Bean
    public Consumer<KStream<String, Customer>> savePerson() {
        return customerConsumer.consume(SAVE_PERSON, customerService::saveUser);
    }
}

