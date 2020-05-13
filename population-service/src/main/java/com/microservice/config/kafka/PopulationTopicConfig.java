package com.microservice.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class PopulationTopicConfig {

    @Bean
    public NewTopic population() {
        return TopicBuilder.name("population")
                .replicas(2)
                .partitions(2)
                .build();
    }
}
