package com.microservice.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.thread.Customer;
import com.microservice.domain.OrderEvent;
import com.microservice.thread.Support;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class RandomRequestProducer implements CommandLineRunner{

    @Autowired
    OrderProducerService orderProducerService;

    public static BlockingQueue<OrderEvent> customerBlockingQueue = new ArrayBlockingQueue<OrderEvent>(20);
    public static List<Customer> customerList = new ArrayList<>();
    public static int customerId = 1;
    public static int orderId = 1;
    public static String threadName;

    public static Customer createCustomerThread() {
        log.info("{} lu müşteri oluşturuldu...", customerId);
        threadName = String.valueOf(customerId);
        return new Customer(customerId++, "Thread"+threadName);
    }

    public static Support createSupportThread() {
        log.info("{} lu müşteri oluşturuldu...", customerId);
        threadName = String.valueOf(customerId);
        return new Support(customerBlockingQueue);
    }

    public void send(){
        Random random = new Random();
        boolean isStart = false;
        while (true) {
            Customer customer = null;
            if (random.nextInt(5) == 1) {
                System.out.println("---------------");
                customer = RandomRequestProducer.createCustomerThread();
                customer.start();
                customerList.add(customer);
            }

            if (!customerBlockingQueue.isEmpty()){
                try {
                    OrderEvent orderEvent = customerBlockingQueue.take();
                    orderEvent.getOrder().setOrderId(orderId++);
                    orderProducerService.sendOrder(orderEvent);
                    log.info("Queue size : {}, Thread: {}", customerBlockingQueue.size(), Thread.currentThread().getName());
                    System.out.println();
                } catch (InterruptedException  | JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            /*if (customerList.size() == 30){
                Thread thread = new Thread(() -> {
                    try {
                        OrderEvent orderEvent = customerBlockingQueue.take();
                        orderEvent.getOrder().setOrderId(orderId++);
                        orderProducerService.sendOrder(orderEvent);
                        log.info("Queue size : {}, Thread: {}", customerBlockingQueue.size(), Thread.currentThread());
                        System.out.println();
                        log.info("Yeni thread oluşturuldu...");
                    } catch (InterruptedException  | JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
            }*/

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {
        send();
    }

    /*@Autowired
    OrderProducerService orderProducerService;

    public static List<Customer> customerList = new ArrayList<>();
    public static int customerId = 1;
    public static int orderId = 1;
    public static String threadName;

    public static Customer createThread() {
        log.info("{} lu müşteri oluşturuldu...", customerId);
        threadName = String.valueOf(customerId);
        ProductChoice productChoice = new ProductChoice();
        OrderEvent orderEvent = productChoice.createCart();
        return new Customer(customerId++, "Thread"+threadName, orderEvent);
    }

    public void send(){
        Random random = new Random();

        while (true) {
            Customer customer = null;
            if (random.nextInt(3) == 1) {
                System.out.println("---------------");
                customer = RandomRequestProducer.createThread();
                customer.start();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customerList.add(customer);
                customerList.stream().forEach(
                        customer1 -> {
                            try {
                                OrderEvent orderEvent = customer1.getOrderEvent();
                                orderEvent.getOrder().setOrderId(orderId++);
                                orderProducerService.sendOrder(orderEvent);
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
        }
    }*/
}
