package com.microservice.config.kafka.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserProducerConfig {
/*    @Bean
    public ProducerFactory<String, User> transactionProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, User> greetingKafkaTemplate() {
        return new KafkaTemplate<>(transactionProducerFactory());
    }*/
}
