package com.greglturnquist.learningspringboot.learningspringboot;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-02 11:21
 **/
@Data
@NoArgsConstructor
@Document
public class Image {
    private String id;
    private String name;
    public Image(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
