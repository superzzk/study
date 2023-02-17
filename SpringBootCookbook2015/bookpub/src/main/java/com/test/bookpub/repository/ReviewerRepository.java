package com.test.bookpub.repository;

import com.test.bookpub.entity.Reviewer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReviewerRepository extends
        PagingAndSortingRepository<Reviewer, Long> {
}
