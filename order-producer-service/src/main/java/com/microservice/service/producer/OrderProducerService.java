package com.microservice.service.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.domain.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@Async
public class OrderProducerService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendOrder(OrderEvent orderEvent) throws JsonProcessingException {
        String key = orderEvent.getOrderEventId();
        String value = objectMapper.writeValueAsString(orderEvent.getOrder());
        ListenableFuture<SendResult<String, String>> order = kafkaTemplate.send("order-message", key, value);
        order.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Order gönderilemedi... {}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Order gönderildi... {}", result.toString());
                log.info("orderEventId : {}, value : {}, partition : {}", key, value, result.getRecordMetadata().partition());
            }
        });

    }
}
