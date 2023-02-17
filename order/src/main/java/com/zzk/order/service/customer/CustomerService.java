package com.zzk.order.service.customer;

import com.zzk.order.entity.Customer;
import com.zzk.order.entity.CustomerExample;
import com.zzk.order.entity.Order;
import com.zzk.order.entity.Product;
import com.zzk.order.entity.ProductExample;
import com.zzk.order.mapper.CustomerMapper;
import com.zzk.order.service.order.IOrderService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    IOrderService orderService;

    public Customer getById(Long id){
        return customerMapper.selectByPrimaryKey(id);
    }

    public Customer getByCode(String code){
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();

        criteria.andCodeEqualTo(code);

        return getOneCustomer(example);
    }


    private Customer getOneCustomer(CustomerExample example) {
        List<Customer> list = customerMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            throw new RuntimeException("Not only one customer found.");
        }
        return list.get(0);
    }

    public List<Order> getOrdersByCustomerCode(String code){
        return orderService.getOrdersByCustomerCode(code);
    }

    public List<Order> getOrdersByCustomerCodeEnhance(String code){
        return orderService.getOrdersByCustomerCodeEnhance(code);
    }

    public List<Customer> get100RandomCustomer(){
        CustomerExample example = new CustomerExample();
        int count = customerMapper.countByExample(example);
        RowBounds rowBounds = new RowBounds(count - 100, 100);

        return customerMapper.selectByExampleWithRowbounds(example, rowBounds);
    }
}
