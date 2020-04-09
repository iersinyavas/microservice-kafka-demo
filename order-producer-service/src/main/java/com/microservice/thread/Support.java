package com.microservice.thread;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.domain.OrderEvent;

import java.util.concurrent.BlockingQueue;

public class Support extends Thread{

    public Support(BlockingQueue<OrderEvent> customerBlockingQueue){

    }

    @Override
    public void run() {
        /*try {
            OrderEvent orderEvent = customerBlockingQueue.take();
            orderEvent.getOrder().setOrderId(orderId++);
            orderProducerService.sendOrder(orderEvent);
            log.info("Queue size : {}, Thread: {}", customerBlockingQueue.size(), Thread.currentThread());
            System.out.println();
            log.info("Yeni thread oluşturuldu...");
        } catch (InterruptedException  | JsonProcessingException e) {
            e.printStackTrace();
        }*/
    }
}
