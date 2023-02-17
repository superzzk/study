package com.greglturnquist.learningspringboot.learningspringboot;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @program: learning-spring-boot
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-02-01 16:05
 **/

/*
* ReactiveCrudRepository extends Repository, a Spring Data Commons marker
* interface that signals Spring Data to create a concrete implementation
* based on the reactive paradigm while also capturing domain information.
* It also comes with some predefined CRUD operations (save, delete,
* deleteById, deleteAll, findById, findAll, and more)
*
* It specifies the entity type (Chapter) and the type of the primary key (String)
*/
public interface ChapterRepository extends ReactiveCrudRepository<Chapter, String> {
}
