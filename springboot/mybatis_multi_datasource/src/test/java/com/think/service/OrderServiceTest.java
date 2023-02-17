package com.think.service;

import com.think.entity.Customer;
import com.think.entity.Order;
import com.think.entity.OrderDetail;
import com.think.entity.Product;
import com.think.main.Application;
import com.think.test1.dao.CustomerMapper;
import com.think.test1.dao.OrderDetailMapper;
import com.think.test1.dao.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);
    @Autowired
    OrderService orderService;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;


    /**
     * 根据customer id查询订单
     */
    @Test
    public void testGetByCustomerId() {
        List<Order> orders = orderService.getByCustomerId(59L);
        for(Order o : orders){
            logger.info("order id:"+o.getId());
        }
    }

    /**
     * 根据order no查询
     */
    @Test
    public void testGetByOrderNo() {
        Order order = orderService.getByOrderNo("e03afcf6-3c17-4aec-830e-fbe548b4e930");
        if(order != null)
            logger.info(order.toString());
    }

    /**
     * 测试插入，根据customerid分表插入
     */
    @Test
    public void test3() {
        Date dt = new Date();
        Order order = new Order();
        String orderNumber = UUID.randomUUID().toString();

        order.setOrderNumber(orderNumber);
        order.setOrderDate(dt);
        order.setShippedDate(dt);
        order.setCustomerId(4);
        orderService.insert(order);

        order = new Order();
        orderNumber = UUID.randomUUID().toString();

        order.setOrderNumber(orderNumber);
        order.setOrderDate(dt);
        order.setShippedDate(dt);
        order.setCustomerId(5);
        orderService.insert(order);
    }

    /**
     * 生成测试数据
     */
    @Test
    public void generateTestData() {
        long startTime = System.currentTimeMillis();    //获取开始时间

        Random rand = new Random();
        Date dt = new Date();

        for(int i=0; i<100; i++) {
            Customer customer = new Customer();
            customer.setName("name"+i);
            customer.setSex( i%2==0? "male" : "female");
            customer.setAge(rand.nextInt(100));
            customerMapper.insert(customer);

            Order order = new Order();
            String orderNumber = UUID.randomUUID().toString();
            order.setOrderNumber(orderNumber);
            order.setOrderDate(dt);
            order.setShippedDate(dt);
            order.setCustomerId(customer.getId());
            orderService.insert(order);

            Product product = new Product();
            String productCode = UUID.randomUUID().toString();
            product.setProductCode(productCode);
            product.setProductName("product"+i);
            product.setProductVendor("vendor"+i);
            product.setBuyPrice((long) rand.nextInt(20000));
            product.setProductDescription("description"+i);
            productMapper.insert(product);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderNumber(orderNumber);
            orderDetail.setProductCode(productCode);
            orderDetail.setQuantityOrdered(rand.nextInt(10));
            orderDetail.setPriceEach(rand.nextDouble());
            orderDetailMapper.insert(orderDetail);

        }

        long endTime = System.currentTimeMillis();    //获取结束时间
        logger.info("程序运行时间：" + (endTime - startTime) / 1000 + "s");
    }
}
