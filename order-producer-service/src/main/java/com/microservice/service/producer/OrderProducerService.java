package com.microservice.service.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.domain.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Arrays;

@Slf4j
@Service
public class OrderProducerService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    String topic1 = "order";
    String topic2 = "sale";

    public synchronized void send(OrderEvent orderEvent) throws JsonProcessingException {
        String value = objectMapper.writeValueAsString(orderEvent);
        ListenableFuture<SendResult<String, String>> order = kafkaTemplate.send(topic1, 0, "order-1-order", value);

        order.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Order gönderilemedi... {}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Order gönderildi... {}", result.toString());
                log.info("orderEventId : {}, value : {}, partition : {}", orderEvent.getOrderEventId(), value, result.getRecordMetadata().partition());
            }
        });
    }

}
