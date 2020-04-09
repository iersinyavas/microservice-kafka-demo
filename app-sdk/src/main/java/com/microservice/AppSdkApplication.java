package com.microservice;

import com.microservice.domain.OrderEvent;
import com.microservice.options.ProductChoice;
import com.microservice.options.ProductEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppSdkApplication {

    public static void main1(String[] args) {
        SpringApplication.run(AppSdkApplication.class, args);
    }

    public static void main(String[] args) {
        ProductChoice productChoice = new ProductChoice();
        System.out.println(productChoice.createCart().toString());
    }
}
