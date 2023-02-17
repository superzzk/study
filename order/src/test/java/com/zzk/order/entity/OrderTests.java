package com.zzk.order.entity;

import com.zzk.order.OrderApplication;
import com.zzk.order.mapper.OrderDetailMapper;
import com.zzk.order.mapper.OrderInfoMapper;
import com.zzk.order.service.customer.CustomerService;
import com.zzk.order.service.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = OrderApplication.class)
class OrderTests {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;


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
            for(int x=0; x<10; x++) {
                List<Product> products = productService.get100RandomProduct();
                List<Customer> customers = customerService.get100RandomCustomer();
                if (products.size() < 100 || customers.size() < 100)
                    return;
                for (int i = 0; i < 100; i++) {
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setOrderCode(UUID.randomUUID().toString());
                    Date now = new Date();
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(now);
                    ca.add(Calendar.DATE, 3);
                    orderInfo.setOrderDate(now);
                    orderInfo.setShippedDate(ca.getTime());
                    orderInfo.setStatus("");
                    orderInfo.setComments("");
                    orderInfo.setCustomerCode(customers.get(i).getCode());
                    orderInfoMapper.insert(orderInfo);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderCode(orderInfo.getOrderCode());
                    orderDetail.setProductCode(products.get(i).getProductCode());
                    orderDetail.setQuantityOrdered(random.nextInt(100));
                    orderDetail.setPriceEach((double) random.nextInt(1000));
                    orderDetail.setOrderLineNumber(random.nextInt(100));
                    orderDetailMapper.insert(orderDetail);
                }
            }
        }
    }

}
