package com.microservice.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Random;

@Slf4j
@Service
@Async
@Data
public class SaleProducerService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    int plus = 0;
    Random random = new Random();

    public void sendSale() {

        int option = random.nextInt(3);
        if (option < 1) {
            setPlus(getPlus() + random.nextInt(10));
        } else if (option > 1) {
            setPlus(getPlus() - random.nextInt(10));
        }

        ListenableFuture<SendResult<String, String>> sale = kafkaTemplate.send("sale", Integer.toString(getPlus()));
        sale.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Veri gönderilemedi...");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Veri gönderildi...");
                log.info("Veri : {}", result.toString());
            }
        });

    }
}
