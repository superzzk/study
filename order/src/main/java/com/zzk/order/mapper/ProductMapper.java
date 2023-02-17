package com.zzk.order.mapper;

import com.zzk.order.entity.Product;
import com.zzk.order.entity.ProductExample;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface ProductMapper {
    int countByExample(ProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExampleWithBLOBsWithRowbounds(ProductExample example, RowBounds rowBounds);

    List<Product> selectByExampleWithBLOBs(ProductExample example);

    List<Product> selectByExampleWithRowbounds(ProductExample example, RowBounds rowBounds);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);
}