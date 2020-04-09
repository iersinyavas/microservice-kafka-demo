package com.microservice.controller;

import com.microservice.domain.User;
import com.microservice.service.producer.UserProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserProducerService userProducerService;

    @Autowired
    public UserController(UserProducerService userProducerService) {
        this.userProducerService = userProducerService;
    }

    @PostMapping(value = "/send")
    public void sendUser(@RequestBody User user){
        this.userProducerService.send(user);
        log.info("Veri gönderildi... : {}", user);
    }

}