package com.test.bookpub.repository;

import com.test.bookpub.entity.Publisher;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PublisherRepository extends
        PagingAndSortingRepository<Publisher, Long> {
}
