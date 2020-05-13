package com.microservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderEvent {
    private String orderEventId;
    private String serverPort;
    private Long currentTotal;
    private Order order;
}
