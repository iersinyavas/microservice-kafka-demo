package com.microservice.controller.socket;

import com.microservice.domain.Order;
import com.microservice.domain.response.OrderResponse;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerSaleController {

    public OrderResponse order(){
        return new OrderResponse();
    }
}
