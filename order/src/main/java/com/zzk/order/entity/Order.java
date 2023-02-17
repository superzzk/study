package com.zzk.order.entity;

import lombok.Data;

/**
 * @author zhangzhongkun
 * @since 2019-12-25 08:49
 **/
@Data
public class Order {
    private OrderInfo orderInfo;
    private OrderDetail orderDetail;

    public Order(OrderInfo info, OrderDetail detail){
        this.orderInfo = info;
        this.orderDetail = detail;
    }

    public Order() {}
}
