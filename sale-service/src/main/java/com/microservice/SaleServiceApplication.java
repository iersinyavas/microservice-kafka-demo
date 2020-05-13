package com.microservice;

import com.microservice.service.producer.SaleProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaleServiceApplication implements CommandLineRunner {

    @Autowired
    SaleProducerService saleProducerService;

    public static void main(String[] args) {
        SpringApplication.run(SaleServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*while (true){
            saleProducerService.sendSale();
            Thread.sleep(2000);
        }*/
    }

}
