package com.greglturnquist.learningspringboot.learningspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/*
 * The @SpringBootApplication annotation tells Spring Boot, when launched,
 * to scan recursively for Spring components inside this package and register
 * them. It also tells Spring Boot to enable autoconfiguration,
 * a process where beans are automatically created based on classpath settings,
 * property settings, and other factors. We'll see more of this throughout
 * the book. Finally, it indicates that this class itself can be
 * a source for Spring bean definitions.
* */
@SpringBootApplication
public class LearningSpringBootApplication {

    /*
    * There is no need to drop this code into an application server
    * or servlet container. We can just run it straight up, inside our IDE.
    * */
    public static void main(String[] args) {
        SpringApplication.run(LearningSpringBootApplication.class, args);
    }

}

