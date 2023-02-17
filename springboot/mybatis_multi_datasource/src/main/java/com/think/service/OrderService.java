package com.think.service;

import com.think.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    @Qualifier("test1OrderMapper")
    private com.think.test1.dao.OrderMapper test1OrderMapper;

    /**
     * 根据order number查询
     */
    public Order getByOrderNo(String orderNo){
        return test1OrderMapper.selectByOrderNo(orderNo);
    }

    public List<Order> getByCustomerId(Long id){
        Integer tableNo = Math.toIntExact(id % 2);
        return test1OrderMapper.selectByCustomerId("t_order_"+tableNo.toString(),id);
    }

    /**
     * 根据customer id做分表插入
     */
    public void insert(Order order){
        Integer tableNo = Math.toIntExact(order.getCustomerId() % 2);
        Map<String, Object> map = new HashMap<>();
        map.put("tableNo", "t_order_"+tableNo.toString());
        map.put("order", order);
        test1OrderMapper.insert(map);
    }
}
