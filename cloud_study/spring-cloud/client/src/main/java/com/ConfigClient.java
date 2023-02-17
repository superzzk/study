package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient  // 在注册中心发现服务
@SpringBootApplication
public class ConfigClient {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient.class, args);
    }

    @Value("${from}")  // 从对应的配置中心找到文件并把属性注入到value值中
    private String value;

    @RequestMapping("/hello")
    public String hello() {
        return "hello" + value ;
    }
}