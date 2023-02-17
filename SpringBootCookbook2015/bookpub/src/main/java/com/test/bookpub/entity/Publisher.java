package com.test.bookpub.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @program: bookpub
 * @description:
 * @author: zhangzhongkun
 * @create: 2019-01-21 21:13
 **/
@Entity
public class Publisher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
    protected Publisher() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Publisher(String name) {
        this.name = name;
    }
}

