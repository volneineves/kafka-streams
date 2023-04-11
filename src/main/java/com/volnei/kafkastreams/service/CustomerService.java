package com.volnei.kafkastreams.service;

import com.volnei.kafkastreams.model.entity.Customer;
import com.volnei.kafkastreams.stream.CustomerPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.volnei.kafkastreams.repository.CustomerRepository;

import java.util.List;

import static com.volnei.kafkastreams.enums.KafkaTopicsEnum.SAVE_PERSON;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerPublisher publisher;

    @Autowired
    public CustomerService(CustomerRepository repository, CustomerPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }


    public List<Customer> findAllUsers() {
        return repository.findAll();
    }

    // POST
    public Customer createUser(Customer customer) {
        publisher.publish(SAVE_PERSON, customer);
        return customer;
    }

    // KAFKA
    public void saveUser(Customer customer) {
        repository.save(customer);
    }
}
