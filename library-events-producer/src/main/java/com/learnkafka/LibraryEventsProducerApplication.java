package com.learnkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LibraryEventsProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryEventsProducerApplication.class, args);
    }

}
