package com.microservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private Integer orderId;
    private Integer customerId;
    private List<Product> productList;
    private String date;
    private Integer cartQuantity;
    private Double cartTotal;
}
