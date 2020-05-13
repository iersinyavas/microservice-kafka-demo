package com.microservice;

import com.microservice.service.producer.PopulationProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;

@EnableDiscoveryClient
@SpringBootApplication
public class PopulationServiceApplication implements CommandLineRunner {

    @Autowired
    PopulationProducerService populationProducerService;

    public static void main(String[] args) {
        SpringApplication.run(PopulationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            populationProducerService.sendPopulation();
            Thread.sleep(2000);
        }
    }

}
