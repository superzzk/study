package com.example.event.listener;

import com.example.event.event.MyApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<MyApplicationEvent> {

    public void onApplicationEvent(MyApplicationEvent event) {
        System.out.println("接收到事件："+event.getClass());
    }

}