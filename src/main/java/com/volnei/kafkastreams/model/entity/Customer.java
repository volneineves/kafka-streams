package com.volnei.kafkastreams.model.entity;

import com.volnei.kafkastreams.model.util.Identifiable;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Customer implements Identifiable {

    @Id
    private UUID id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public Customer() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
