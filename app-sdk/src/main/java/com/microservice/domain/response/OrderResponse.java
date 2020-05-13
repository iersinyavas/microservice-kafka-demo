package com.microservice.domain.response;

import com.microservice.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    Order order;
}
