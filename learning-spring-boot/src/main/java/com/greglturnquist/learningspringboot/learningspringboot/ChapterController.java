package com.greglturnquist.learningspringboot.learningspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-01 16:14
 **/
@RestController
public class ChapterController {
    private final ChapterRepository repository;
    /*
    * Constructor injection is used to automatically load it with a copy of
    * ChapterRepository. With Spring, if there is only one constructor call,
    * there is no need to include an @Autowired annotation
    * */
    public ChapterController(ChapterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/chapters")
    public Flux<Chapter> listing() {
        return repository.findAll();
    }
}
