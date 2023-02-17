package com.think;

import com.think.entity.Order;
import com.think.main.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AppTest {

    @Autowired
    @Qualifier("test1OrderMapper")
    private com.think.test1.dao.OrderMapper test1OrderMapper;


    @Test
    public void test2() {
        Order order = test1OrderMapper.selectByPrimaryKey("0",3L);
        System.out.println(order.getId() + "" + order.getCustomerId());
    }

}
