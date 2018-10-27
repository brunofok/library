package com.example.demo.controller;


import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @PostMapping(path = "/books",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Book> createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Book> findBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(value = "/books/{isbn}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Book>> findBookByIsbn(@PathVariable(value = "isbn") String isbn) {
        return bookRepository.findById(isbn)
                .map(book -> ResponseEntity.ok(book))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/booksWithDelay", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Book> findBooksWithDelay() {
        return bookRepository.findAll().delayElements(Duration.ofMillis(2000));
    }
}