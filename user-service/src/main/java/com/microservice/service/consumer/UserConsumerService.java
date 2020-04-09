package com.microservice.service.consumer;

import com.microservice.domain.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumerService {

    @KafkaListener(topics = "user", containerFactory = "kafkaListenerContainerFactory")
    public void consume(User user){
        System.out.println(user.toString());
    }

}