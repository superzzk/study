package com.think.test1.dao;

import com.think.entity.Order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component("test1OrderMapper")
public interface OrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Map<String,Object> record);

    Order selectByPrimaryKey(@Param("tableNo") String tableNo,@Param("id") Long id);

    List<Order> selectByCustomerId(@Param("tableNo") String tableNo,@Param("customerId") Long id);

    Order selectByOrderNo(String orderNo);

    int updateByPrimaryKey(Order record);
}