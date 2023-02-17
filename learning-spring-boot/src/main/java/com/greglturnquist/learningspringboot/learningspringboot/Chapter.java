package com.greglturnquist.learningspringboot.learningspringboot;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-01 15:58
 **/

/*
* The @Data annotation from Lombok generates getters, setters, a toString()
* method, an equals() method, a hashCode() method, and a constructor for all
* required (that is, final) fields
*
* The @Document annotation flags this class as suitable for storing in a
* MongoDB data store
* */
@Data
@Document
public class Chapter {
    //indicating this is the primary key of our Mongo document
    @Id
    private String id;
    private String name;
    public Chapter(String name) {
        this.name = name;
    }
}
