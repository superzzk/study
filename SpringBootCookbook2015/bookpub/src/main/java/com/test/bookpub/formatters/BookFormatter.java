package com.test.bookpub.formatters;

import com.test.bookpub.entity.Book;
import com.test.bookpub.repository.BookRepository;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;

import java.util.Locale;

public class BookFormatter implements Formatter<Book> {
    private BookRepository repository;

    public BookFormatter(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book parse(String bookIdentifier, Locale locale) throws
            ParseException {
        Book book = repository.findBookByIsbn(bookIdentifier);
        return book;
    }

    @Override
    public String print(Book book, Locale locale) {
        return book.getIsbn();
    }
}