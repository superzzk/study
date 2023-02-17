package com.greglturnquist.learningspringboot.learningspringboot.data;

import com.greglturnquist.learningspringboot.learningspringboot.Image;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-02 15:27
 **/
@Component
public class InitDatabase {
    @Bean
    CommandLineRunner init(MongoOperations operations) {
        return args -> {
            operations.dropCollection(Image.class);
            operations.insert(new Image("1",
                    "learning-spring-boot-cover.jpg"));
            operations.insert(new Image("2",
                    "learning-spring-boot-2nd-edition-cover.jpg"));
            operations.insert(new Image("3",
                    "bazinga.png"));
            operations.findAll(Image.class).forEach(image -> {
                System.out.println(image.toString());
            });
        };
    }
}