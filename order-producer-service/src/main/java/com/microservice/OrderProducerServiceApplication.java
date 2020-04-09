package com.microservice;

import com.microservice.service.producer.RandomRequestProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class OrderProducerServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(OrderProducerServiceApplication.class, args);
	}

	@Bean
	public RandomRequestProducer randomRequestProducer(){
		return new RandomRequestProducer();
	}

}
