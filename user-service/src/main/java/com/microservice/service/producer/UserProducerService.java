package com.microservice.service.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducerService {
    private static final String TOPIC_NAME ="user";

    /*private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public UserProducerService(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(User user){
        kafkaTemplate.send(TOPIC_NAME,user);
    }*/
}