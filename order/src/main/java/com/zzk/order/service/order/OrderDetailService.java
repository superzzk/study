package com.zzk.order.service.order;

import com.zzk.order.entity.OrderDetail;
import com.zzk.order.entity.OrderDetailExample;
import com.zzk.order.entity.OrderInfo;
import com.zzk.order.entity.OrderInfoExample;
import com.zzk.order.mapper.OrderDetailMapper;
import com.zzk.order.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    public void save(OrderDetail record){
        if(record == null)
            return;
        if(record.getId()==null){
            orderDetailMapper.insert(record);
        }
        else{
            orderDetailMapper.updateByPrimaryKey(record);
        }
    }

    public OrderDetail getById(Long id){
        return orderDetailMapper.selectByPrimaryKey(id);
    }

    public OrderDetail getByOrderCode(String code){
        OrderDetailExample example = new OrderDetailExample();
        OrderDetailExample.Criteria criteria = example.createCriteria();

        criteria.andOrderCodeEqualTo(code);

        return getOne(example);
    }

    private OrderDetail getOne(OrderDetailExample example) {
        List<OrderDetail> list = orderDetailMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            throw new RuntimeException("Not only one orderDetail found.");
        }
        return list.get(0);
    }

}
