package com.test.bookpub.repository;

import com.test.bookpub.entity.Author;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "writers",
        path = "writers")
public interface AuthorRepository extends
        PagingAndSortingRepository<Author, Long> {
}
