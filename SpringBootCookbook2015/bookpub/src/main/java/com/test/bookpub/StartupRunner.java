package com.test.bookpub;
import com.test.bookpub.entity.Author;
import com.test.bookpub.entity.Book;
import com.test.bookpub.entity.Publisher;
import com.test.bookpub.repository.AuthorRepository;
import com.test.bookpub.repository.BookRepository;
import com.test.bookpub.repository.PublisherRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;

import javax.sql.DataSource;

public class StartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public void run(String... args) throws Exception {
        Author author = new Author("Alex", "Antonov");
        author = authorRepository.save(author);
        Publisher publisher = new Publisher("Packt");
        publisher = publisherRepository.save(publisher);
        Book book = new Book("978-1-78528-415-1", "Spring Boot Recipes",
                author, publisher);
        bookRepository.save(book);
        logger.info("Number of books: " + bookRepository.count());

    }

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void run() {
        logger.info("Number of books: " + bookRepository.count());
    }
}