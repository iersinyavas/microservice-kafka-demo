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
    A(1), B(5), C(10), D(20), E(50), F(100), G(200);

    Product product = null;
    Random random = new Random();
    long price;
    int quantity;
    long total;

    ProductEnum(long price){
        this.price = price;
        this.quantity = random.nextInt(random.nextInt(random.nextInt(10)+1)+1)+1;
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
