package com.zzk.order.mapper;

import com.zzk.order.entity.Customer;
import com.zzk.order.entity.CustomerExample;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface CustomerMapper {
    int countByExample(CustomerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExampleWithRowbounds(CustomerExample example, RowBounds rowBounds);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}