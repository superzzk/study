package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 启动运行
  **/
@Component
public class MyApplicationRunner implements ApplicationRunner, Ordered {

    @Value("${from}")
    private String bootstrap_servers;

    @Value("${from.abc}")
    private String from_abc;

    @Override
    public int getOrder(){
        return 1;//通过设置这里的数字来知道指定顺序
    }

    @Override
    public void run(ApplicationArguments var1) throws Exception {

        System.out.println("bootstrap_servers: " + bootstrap_servers + from_abc);
    }

}
