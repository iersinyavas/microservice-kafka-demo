package com.microservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.domain.OrderEvent;
import com.microservice.service.producer.OrderProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    OrderProducerService orderProducerService;

    @PostMapping("/send")
    public ResponseEntity<OrderEvent> sendOrderEvent(@RequestBody OrderEvent orderEvent) throws JsonProcessingException {
        orderProducerService.sendOrder(orderEvent);
        return new ResponseEntity<OrderEvent>(orderEvent, HttpStatus.OK);
    }
}
