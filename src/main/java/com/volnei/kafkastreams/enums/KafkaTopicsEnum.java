package com.volnei.kafkastreams.enums;

// TODO validar com Andre necessidade de utilizar um enum
public enum KafkaTopicsEnum {

    SAVE_PERSON("savePerson");

    public final String name;

    KafkaTopicsEnum(String name) {
        this.name = name;
    }
}
