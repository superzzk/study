package com.greglturnquist.learningspringboot.learningspringboot;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-02 14:40
 **/
@Data
@Document(collection="employees")
public class Employee {
    @Id
    String id;
    String firstName;
    String lastName;
}