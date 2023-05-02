package com.volnei.kafkastreams.enums;

public enum KafkaTopicsEnum {

    SAVE_PERSON("savePerson");

    public final String name;

    KafkaTopicsEnum(String name) {
        this.name = name;
    }
}
