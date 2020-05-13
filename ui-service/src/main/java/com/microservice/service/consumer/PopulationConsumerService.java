package com.microservice.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.domain.Population;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PopulationConsumerService {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    private static int year = 0;

    /*@KafkaListener(groupId = "population-1", topicPartitions = @TopicPartition(topic = "population", partitions = {"0"}))*/
    @KafkaListener(topics = "population")
    public void populationConsume(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        log.info("Gelen değer : {}", consumerRecord);
        Population population = objectMapper.readValue(consumerRecord.value(), Population.class);
        template.convertAndSend("/topic/population", population);
    }
}
