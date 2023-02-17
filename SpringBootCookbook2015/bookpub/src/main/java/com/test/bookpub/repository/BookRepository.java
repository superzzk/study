package com.test.bookpub.repository;

import com.test.bookpub.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    public Book findBookByIsbn(String isbn);


}