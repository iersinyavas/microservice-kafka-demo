package com.microservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderConsumerService {

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "order")
    public void orderListener(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        log.info("partition-0 : {}, Gelen değer : {}", consumerRecord.partition(), consumerRecord);
        Order order = objectMapper.readValue(consumerRecord.value(), Order.class);
    }

/*    @KafkaListener(groupId = "order-1", topicPartitions = @TopicPartition(topic = "order", partitions = {"0"}))
    public void orderListener(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        log.info("partition-0 : {}, Gelen değer : {}", consumerRecord.partition(), consumerRecord);
        Order order = objectMapper.readValue(consumerRecord.value(), Order.class);
        log.info(order.toString());
    }

    @KafkaListener(groupId = "order-1", topicPartitions = @TopicPartition(topic = "order", partitions = {"1"}))
    public void orderListener1(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        log.info("partition-1 : {}, Gelen değer : {}", consumerRecord.partition(), consumerRecord);
        Order order = objectMapper.readValue(consumerRecord.value(), Order.class);
        log.info(order.toString());
    }*/

}
