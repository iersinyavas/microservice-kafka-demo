package com.microservice.options;

import com.microservice.domain.Order;
import com.microservice.domain.OrderEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class ProductChoice {

    Random random = new Random();
    private List<ProductEnum> productEnumList = Arrays.asList(
            ProductEnum.A,
            ProductEnum.B,
            ProductEnum.C,
            ProductEnum.D,
            ProductEnum.E,
            ProductEnum.F,
            ProductEnum.G
    );

    public synchronized OrderEvent createCart(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String pattern = "yyyy-dd-MM HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        String dateString = simpleDateFormat.format(date);
        Order order = Order.builder()
                .orderId(null)
                .customerId(null)
                .productList(new ArrayList<>())
                .date(dateString)
                .build();
        IntStream.range(0, random.nextInt(7)+1).forEach(
                value -> {
                    order.getProductList().add(productEnumList.get(random.nextInt(7)).assignToProduct());
                }
        );
        order.setCartTotal(order.getProductList().stream().map(product -> product.getPrice() * product.getQuantity()).reduce(0L, (a, b) -> Long.sum(a, b)));
        order.setCartQuantity(order.getProductList().stream().map(product -> product.getQuantity()).reduce(0, (a,b) -> a + b));

        return OrderEvent.builder()
                .orderEventId(Long.toString(System.currentTimeMillis()))
                .order(order)
                .build();
    }
}
