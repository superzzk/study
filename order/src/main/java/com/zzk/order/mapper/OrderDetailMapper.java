package com.zzk.order.mapper;

import com.zzk.order.entity.OrderDetail;
import com.zzk.order.entity.OrderDetailExample;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface OrderDetailMapper {
    int countByExample(OrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    List<OrderDetail> selectByExampleWithRowbounds(OrderDetailExample example, RowBounds rowBounds);

    List<OrderDetail> selectByExample(OrderDetailExample example);

    OrderDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}