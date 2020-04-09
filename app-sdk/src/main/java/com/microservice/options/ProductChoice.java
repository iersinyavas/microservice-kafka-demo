package com.microservice.options;

import com.microservice.domain.Order;
import com.microservice.domain.OrderEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.IntStream;

public class ProductChoice {

    Random random = new Random();
    private List<ProductEnum> productEnumList = Arrays.asList(
            ProductEnum.Monitor,
            ProductEnum.Keyboard,
            ProductEnum.Laptop,
            ProductEnum.Mouse
    );

    public synchronized OrderEvent createCart(){
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
        IntStream.range(0, random.nextInt(4)+1).forEach(
                value -> {
                    order.getProductList().add(productEnumList.get(random.nextInt(4)).assignToProduct());
                }
        );
        order.setCartTotal(order.getProductList().stream().map(product -> product.getPrice() * product.getQuantity()).reduce(0.0, (a, b) -> Double.sum(a, b)));
        order.setCartQuantity(order.getProductList().stream().map(product -> product.getQuantity()).reduce(0, (a,b) -> a + b));
        String orderEventId = ""+(date.getYear()+1900)+date.getMonth()+date.getDate()+date.getDay()+date.getHours()+date.getMinutes()+date.getSeconds();

        return OrderEvent.builder()
                .orderEventId(orderEventId)
                .order(order)
                .build();
    }
}
