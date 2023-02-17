package com.greglturnquist.learningspringboot.learningspringboot.data;

import com.greglturnquist.learningspringboot.learningspringboot.Image;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-02 15:25
 **/
public interface ImageRepository extends ReactiveCrudRepository<Image, String> {
        Mono<Image> findByName(String name);
}