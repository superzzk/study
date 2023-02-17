package com.zzk.order.entity;

import com.zzk.order.OrderApplication;
import com.zzk.order.mapper.CustomerMapper;
import com.zzk.order.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = OrderApplication.class)
class ProductTests {

    @Autowired
    ProductMapper productMapper;


    @Test
    void initDb() throws InterruptedException {
        System.out.println("start: " + new Date().toString());
        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0; i<10; i++)
            executor.execute(new InsertTask());

        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.MINUTES);
        System.out.println("end: " + new Date().toString());
    }

    class InsertTask implements Runnable{
        @Override
        public void run() {
            Random random  = new Random();
            for(int i=0; i<10000; i++) {
                Product product = new Product();
                product.setProductCode(UUID.randomUUID().toString());
                product.setProductName("name");
                product.setProductLine("line");
                product.setProductScale("scale");
                product.setProductVendor(random.nextBoolean() ? "aws" : "google");
                product.setQuantityInStock(random.nextInt(1000));

                product.setBuyPrice((long)random.nextInt(999999));
                product.setProductDescription("");

                productMapper.insert(product);
            }
        }
    }

}
