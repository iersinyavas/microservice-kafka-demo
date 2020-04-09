package com.microservice.options;

import com.microservice.domain.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
@Getter
public enum ProductEnum {
    Monitor(900.0), Keyboard(500.0), Mouse(50.0), Laptop(3500.0);

    Product product = null;
    Random random = new Random();
    double price;
    int quantity;
    double total;

    ProductEnum(double price){
        this.price = price;
        this.quantity = random.nextInt(5)+1;
        this.total = this.price * this.quantity;
    }

    public Product assignToProduct(){
        product = new Product();
        product.setName(this.name());
        product.setPrice(this.getPrice());
        product.setProductId(this.ordinal()+1);
        product.setQuantity(this.quantity);
        product.setTotal(getTotal());
        return product;
    }
}
