package com.zzk.order.mapper;

import com.zzk.order.entity.OrderInfo;
import com.zzk.order.entity.OrderInfoExample;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface OrderInfoMapper {
    int countByExample(OrderInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    List<OrderInfo> selectByExampleWithRowbounds(OrderInfoExample example, RowBounds rowBounds);

    List<OrderInfo> selectByExample(OrderInfoExample example);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
}