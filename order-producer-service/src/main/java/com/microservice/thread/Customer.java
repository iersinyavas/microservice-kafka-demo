package com.microservice.thread;

import com.microservice.domain.OrderEvent;
import com.microservice.options.ProductChoice;
import com.microservice.service.producer.RandomRequestProducer;
import lombok.Data;

import java.util.Random;

@Data
public class Customer extends Thread {

    int customerId;
    private OrderEvent orderEvent = null;

    public Customer(int customerId, String customerName) {
        this.customerId = customerId;
        this.setName(customerName);
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {

            spend();
            try {
                if (getOrderEvent() != null) {
                    RandomRequestProducer.customerBlockingQueue.put(getOrderEvent());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(random.nextInt(60000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private OrderEvent spend(){
        ProductChoice productChoice = new ProductChoice();
        setOrderEvent(productChoice.createCart());
        getOrderEvent().getOrder().setCustomerId(getCustomerId());
        return this.getOrderEvent();
    }

}