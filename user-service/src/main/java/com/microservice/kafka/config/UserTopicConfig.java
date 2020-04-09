package com.microservice.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UserTopicConfig {

    @Bean
    public NewTopic order(){
        return TopicBuilder.name("user")
                .replicas(2)
                .partitions(2)
                .build();
    }
}
