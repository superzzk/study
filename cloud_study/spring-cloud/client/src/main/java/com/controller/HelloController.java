package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //发现注册服务
    @Autowired
    DiscoveryClient discoveryClient;


    @RequestMapping("/info")
    public Object info() {
        return discoveryClient.getServices();
    }
}