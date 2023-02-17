package com.zzk.order.entity;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String code;
    private String name;
    private Integer age;
    private String sex;
    private String memo;

}