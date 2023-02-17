package com.example.event;

import com.example.event.event.MyApplicationEvent;
import com.example.event.publisher.DemoPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EventApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EventApplication.class, args);
        //发布事件
        context.publishEvent(new MyApplicationEvent(new Object()));

        DemoPublisher publisher = context.getBean(DemoPublisher.class);
        publisher.publish("hello world!");
        context.close();
    }
}
