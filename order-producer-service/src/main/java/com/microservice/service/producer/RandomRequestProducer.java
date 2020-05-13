package com.microservice.service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.thread.Customer;
import com.microservice.domain.OrderEvent;
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

    public static BlockingQueue<OrderEvent> customerBlockingQueue = new ArrayBlockingQueue<OrderEvent>(1000);
    public static List<Customer> customerList = new ArrayList<>();
    public static int customerId = 1;
    public static int orderId = 1;
    public static String threadName;

    static Long currentTotal=0L;

    public static Customer createCustomerThread() {
        log.info("{} nolu müşteri oluşturuldu...", customerId);
        threadName = String.valueOf(customerId);
        return new Customer(customerId++, "Thread"+threadName);
    }

    private synchronized static int orderIdPlus(){
        return orderId++;
    }

    @Override
    public void run(String... args) throws Exception {
/*        ThreadControl threadControl = new ThreadControl();
        Thread thread = new Thread(() -> {
            threadControl.continueMethod();
        });

        Thread thread1 = new Thread(() ->{
            threadControl.customerControl();
        });

        Thread thread2 = new Thread(() ->{
            threadControl.queueControl();
        });

        thread.start();
        thread1.start();
        thread2.start();*/

    }

    class ThreadControl{
        Random random = new Random();
        public void customerControl(){
            while(true) {
                synchronized (this) { // lock kullanıldı
                    Customer customer = null;
                    if (random.nextInt(10) == 1) {
                        System.out.println("---------------");
                        customer = RandomRequestProducer.createCustomerThread();
                        customer.start();
                        customerList.add(customer);
                    }

                    try {
                        Thread.sleep(random.nextInt(2000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void queueControl(){
            while (true) {
                synchronized (this) {
                    if (!customerBlockingQueue.isEmpty()) {
                        notify();
                    }
                }
            }
        }

        public void continueMethod() {
            while (true) {
                synchronized (this) {
                    if (!customerBlockingQueue.isEmpty()) {
                        currentTotal += customerBlockingQueue.stream().map(orderEvent -> orderEvent.getOrder().getCartTotal()).reduce(0L, (a, b) -> Long.sum(a, b));
                        customerBlockingQueue.stream().forEach(
                                orderEvent1 -> {
                                    try {
                                        OrderEvent orderEvent = customerBlockingQueue.take();
                                        orderEvent.getOrder().setOrderId(orderIdPlus());

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                });
                        /*customerBlockingQueue.stream().forEach(
                                orderEvent1 -> {
                                    try {
                                        OrderEvent orderEvent = customerBlockingQueue.take();
                                        orderEvent.getOrder().setOrderId(orderIdPlus());
                                        orderProducerService.send(orderEvent);
                                    } catch (InterruptedException | JsonProcessingException e) {
                                        e.printStackTrace();
                                    }
                                });*/
                        OrderEvent orderEvent = new OrderEvent();
                        orderEvent.setCurrentTotal(currentTotal);
                        try {
                            orderProducerService.send(orderEvent);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        System.out.println();
                    } else {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    log.info("Queue size : {}, Thread: {}", customerBlockingQueue.size(), Thread.currentThread().getName());

                }
            }
        }
    }
}
