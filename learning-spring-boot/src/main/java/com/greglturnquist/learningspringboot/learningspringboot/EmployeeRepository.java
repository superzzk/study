package com.greglturnquist.learningspringboot.learningspringboot;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-02 14:40
 **/
public interface EmployeeRepository  extends ReactiveCrudRepository<Employee, Long> {

    Flux<Employee> findByFirstName(Mono<String> name);

}
