package com.zzk.order.service.order;

import com.zzk.order.entity.Customer;
import com.zzk.order.entity.Order;
import com.zzk.order.entity.OrderDetail;
import com.zzk.order.entity.OrderInfo;
import com.zzk.order.entity.Product;
import com.zzk.order.mapper.OrderMapper;
import com.zzk.order.service.customer.CustomerService;
import com.zzk.order.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;

    public Order getOrderById(Long id){
        OrderInfo orderInfo = orderInfoService.getById(id);
        OrderDetail orderDetail = orderDetailService.getByOrderCode(orderInfo.getOrderCode());
        return new Order(orderInfo, orderDetail);
    }

    public List<Order> getOrdersByCustomerCode(String code){
        List<OrderInfo> orderInfos = orderInfoService.getByCustomerCode(code);
        List<Order> orders = new ArrayList<>();
        for(OrderInfo info : orderInfos){
            OrderDetail detail = orderDetailService.getByOrderCode(info.getOrderCode());
            orders.add(new Order(info, detail));
        }
        return orders;
    }
    /**
     * 增强版，一次性取回所有的OrderDetail再进行组装，不需要多次查询数据库
     */
    public List<Order> getOrdersByCustomerCodeEnhance(String code){
        List<OrderInfo> orderInfos = orderInfoService.getByCustomerCode(code);
        List<Order> orders = new ArrayList<>();
        String[] orderCodes = orderInfos.stream().map(OrderInfo::getOrderCode).toArray(String[]::new);
        List<OrderDetail> details = orderMapper.getOrderDetailList(orderCodes);

        for(OrderInfo info : orderInfos){
            OrderDetail detail = details.stream().filter(d -> d.getOrderCode() == info.getOrderCode()).findFirst().orElse(null);
            orders.add(new Order(info, detail));
        }
        return orders;
    }

    /**
     * 启动10个线程，每个线程插入1000条数据
     */
    public void simulator() throws InterruptedException {
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
                    orderInfoService.save(orderInfo);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderCode(orderInfo.getOrderCode());
                    orderDetail.setProductCode(products.get(i).getProductCode());
                    orderDetail.setQuantityOrdered(random.nextInt(100));
                    orderDetail.setPriceEach((double) random.nextInt(1000));
                    orderDetail.setOrderLineNumber(random.nextInt(100));
                    orderDetailService.save(orderDetail);
                }
            }
        }
    }
}
