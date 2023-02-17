package com.zzk.order.service.order;

import com.zzk.order.entity.Order;

import java.util.List;

public interface IOrderService {
    Order getOrderById(Long id);

    List<Order> getOrdersByCustomerCode(String code);

    List<Order> getOrdersByCustomerCodeEnhance(String code);

    void simulator() throws InterruptedException;

}
