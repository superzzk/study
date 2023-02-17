package com.zzk.order.service.order;

import com.zzk.order.entity.OrderInfo;
import com.zzk.order.entity.OrderInfoExample;
import com.zzk.order.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    public void save(OrderInfo record){
        if(record == null)
            return;
        if(record.getId()==null){
            orderInfoMapper.insert(record);
        }
        else{
            orderInfoMapper.updateByPrimaryKey(record);
        }
    }

    public OrderInfo getById(Long id){
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    public OrderInfo getByCode(String code){
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = example.createCriteria();

        criteria.andOrderCodeEqualTo(code);

        return getOne(example);
    }
    public List<OrderInfo> getByCustomerCode(String code){
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = example.createCriteria();

        criteria.andCustomerCodeEqualTo(code);

        return orderInfoMapper.selectByExample(example);
    }


    private OrderInfo getOne(OrderInfoExample example) {
        List<OrderInfo> list = orderInfoMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            throw new RuntimeException("Not only one orderInfo found.");
        }
        return list.get(0);
    }

}
