package com.microservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Integer productId;
    private String name;
    private Double price;
    private Integer quantity;
    private Double total;
}
