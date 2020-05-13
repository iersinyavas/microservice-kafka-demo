package com.microservice.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class OrderTopicConfig {

    @Bean
    public NewTopic order(){
        return TopicBuilder.name("order")
                .replicas(2)
                .partitions(2)
                .build();
    }

    @Bean
    public NewTopic orderSale(){
        return TopicBuilder.name("order-sale")
                .replicas(2)
                .partitions(2)
                .build();
    }

}
