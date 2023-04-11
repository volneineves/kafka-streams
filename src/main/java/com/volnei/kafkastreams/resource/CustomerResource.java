package com.volnei.kafkastreams.resource;

import com.volnei.kafkastreams.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.volnei.kafkastreams.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class CustomerResource {

    private final CustomerService service;

    @Autowired
    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAllUsers() {
        List<Customer> people = service.findAllUsers();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity<Customer> createUser(@RequestBody Customer customer) {
        Customer response = service.createUser(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
