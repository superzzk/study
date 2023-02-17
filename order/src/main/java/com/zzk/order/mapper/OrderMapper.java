package com.zzk.order.mapper;

import com.zzk.order.entity.OrderDetail;
import com.zzk.order.entity.OrderInfo;
import com.zzk.order.entity.OrderInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OrderMapper {
    List<OrderDetail> getOrderDetailList(@Param("orderCodes") String[] orderCodes);
}