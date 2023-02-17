package com.greglturnquist.learningspringboot.learningspringboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-01 16:09
 **/
@Configuration
public class LoadDatabase {
    /*
    * @Bean indicates that the return value of init() is a Spring Bean
    * --​in this case, a CommandLineRunner
    *
    * Spring Boot runs all CommandLineRunner beans after the entire
    * application is up and running. This bean definition requests a
    * copy of ChapterRepository
    * */
    //@Bean
    CommandLineRunner init(ChapterRepository repository) {
        return args -> {
            Flux.just(
                    new Chapter("Quick Start with Java"),
                    new Chapter("Reactive Web with Spring Boot"),
                    new Chapter("...and more!"))
                    .flatMap(repository::save)
                    .subscribe(System.out::println);
        };
    }
}
