package com.example.demo;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

	static final String BOOK_TITLE = "Harry Potter and the Philosopher's Stone";
	static final String BOOK_ISBN = "0-7475-3269-9";

	static final LocalDate BOOK_PUB_DATE = LocalDate.of(1997, Month.JUNE.getValue(), 26);

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	BookRepository bookRepository;

	@Test
	public void testCreateBook() {

		Book newBook = new Book();
		newBook.setIsbn(BOOK_ISBN);
		newBook.setPublicationDate(BOOK_PUB_DATE);
		newBook.setTitle(BOOK_TITLE);

		webTestClient.post().uri("/books")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(newBook), Book.class)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.title").isEqualTo(BOOK_TITLE)
				.jsonPath("$.isbn").isEqualTo(BOOK_ISBN)
				.jsonPath("$.publicationDate").isEqualTo(BOOK_PUB_DATE.toString());

		bookRepository.deleteAll().block();
	}

	@Test
	public void testFindBookByIsbnNotFound() {

		webTestClient.get().uri("/books/{isbn}", Collections.singletonMap("isbn", "random_value"))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isNotFound();
	}

	@Test
	public void testFindBookByIsbn() {
		Book newBook = new Book();
		newBook.setTitle(BOOK_TITLE);
		newBook.setPublicationDate(BOOK_PUB_DATE);
		newBook.setIsbn(BOOK_ISBN);

		bookRepository.save(newBook).block();

		webTestClient.get().uri("/books/{isbn}", Collections.singletonMap("isbn", BOOK_ISBN))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.title").isEqualTo(BOOK_TITLE)
				.jsonPath("$.isbn").isEqualTo(BOOK_ISBN)
				.jsonPath("$.publicationDate").isEqualTo(BOOK_PUB_DATE.toString());


		bookRepository.deleteAll().block();
	}

	@Test
	public void testFindAllBooks() {

		Book newBook = new Book();
		newBook.setTitle(BOOK_TITLE);
		newBook.setPublicationDate(BOOK_PUB_DATE);
		newBook.setIsbn(BOOK_ISBN);

		bookRepository.save(newBook).block();

		webTestClient.get().uri("/books")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBodyList(Book.class)
				.hasSize(1)
				.contains(newBook);

		bookRepository.deleteAll().block();
	}

}
