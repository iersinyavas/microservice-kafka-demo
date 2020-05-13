package com.microservice.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class SaleTopicConfig {

    @Bean
    public NewTopic sale() {
        return TopicBuilder.name("sale")
                .replicas(2)
                .partitions(2)
                .build();
    }

}
