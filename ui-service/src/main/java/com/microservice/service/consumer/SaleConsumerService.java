package com.microservice.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.domain.Order;
import com.microservice.domain.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SaleConsumerService {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    ObjectMapper objectMapper;

    private long cartTotal = 0;

/*    @KafkaListener(topics = "order")
    public void consume(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        log.info("partition-1 : {}, Gelen değer : {}", consumerRecord.partition(), consumerRecord);
        OrderEvent orderEvent = objectMapper.readValue(consumerRecord.value(), OrderEvent.class);
        //Order order = orderEvent.getOrder();
        //cartTotal += orderEvent.getOrder().getCartTotal();
        template.convertAndSend("/topic/order-sale", orderEvent);
        //template.convertAndSend("/topic/customer-sale", order);
    }*/
}
