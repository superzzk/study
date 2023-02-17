package com.zzk.order.service.product;

import com.zzk.order.entity.Product;
import com.zzk.order.entity.ProductExample;
import com.zzk.order.mapper.ProductMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public List<Product> get100RandomProduct(){
        ProductExample example = new ProductExample();
        int count = productMapper.countByExample(example);
        RowBounds rowBounds = new RowBounds(count - 100, 100);

        return productMapper.selectByExampleWithRowbounds(example, rowBounds);
    }

}
