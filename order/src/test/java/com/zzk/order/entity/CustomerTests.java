package com.zzk.order.entity;

import com.zzk.order.OrderApplication;
import com.zzk.order.mapper.CustomerMapper;
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
class CustomerTests {

    @Autowired
    CustomerMapper customerMapper;

    /**
     * 逐条写入数据，速度较慢，约50s/1000条
     */
    @Test
    void initDb() {
        Random random  = new Random();
        for(int i=0; i<1000000; i++) {
            Customer customer = new Customer();

            customer.setCode(UUID.randomUUID().toString());
            customer.setSex(random.nextBoolean() ? "male" : "female");
            customer.setAge(random.nextInt(100));
            customer.setName("name");
            customer.setMemo("memo");
            customerMapper.insert(customer);

            if(i%1000 == 0) {
                System.out.println("current no: " + i + new Date().toString());
            }
        }
    }


    /**
     * 启动线程，每个线程写入1000条
     * 启动4个线程， 用时112s，28/1000条
     * 启动8个线程， 用时113s，11.25s/1000条
     * 启动16个线程，用时180s，14.125s/1000条
     */
    @Test
    void initDb2() throws InterruptedException {
        System.out.println("start: " + new Date().toString());
        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0; i<4; i++)
            executor.execute(new InsertTask());

        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.MINUTES);
        System.out.println("end: " + new Date().toString());
    }

    class InsertTask implements Runnable{
        @Override
        public void run() {
            Random random  = new Random();
            for(int i=0; i<1000; i++) {
                Customer customer = new Customer();

                customer.setCode(UUID.randomUUID().toString());
                customer.setSex(random.nextBoolean() ? "male" : "female");
                customer.setAge(random.nextInt(100));
                customer.setName("name");
                customer.setMemo("memo");
                customerMapper.insert(customer);
            }
        }
    }

}
