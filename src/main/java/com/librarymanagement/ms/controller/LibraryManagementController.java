package com.librarymanagement.ms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.ms.data.Book;
import com.librarymanagement.ms.data.BookSpecificationBuilder;
import com.librarymanagement.ms.data.CSVHelper;
import com.librarymanagement.ms.repository.BookRepository;

/**
 * This class is used to serve the controller class.
 * 
 * @author OM
 *
 */
@RestController
@RequestMapping(LibraryManagementController.PATH)
public class LibraryManagementController {

	Logger logger = LoggerFactory.getLogger(LibraryManagementController.class);

	public static final String PATH = "v1/library/books";

	@Autowired
	private BookRepository bookRepository;

	/**
	 * This method is used to fetch the books with Isbn as a value from DB
	 * 
	 * @param isbn
	 * @return book
	 */
	@GetMapping("/isbn")
	public Optional<Book> searchWithIsbn(@RequestParam(value = "isbn") String isbn) {
		logger.info("received the request::" + isbn);
		return bookRepository.findById(isbn);
	}

	/**
	 * This method is used to fetch the books from db using the search criteria
	 * 
	 * @param search
	 * @return list of books
	 */
	@GetMapping("/search")
	public List<Book> search(@RequestParam(value = "search") String search) {
		logger.info("received a request to search ::" + search);
		BookSpecificationBuilder builder = new BookSpecificationBuilder();
		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
		Matcher matcher = pattern.matcher(search + ",");
		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		Specification<Book> spec = builder.build();
		return bookRepository.findAll(spec);
	}

	/**
	 * This method is used to fetch the books import as csv format
	 * 
	 * @param
	 * @return list of books
	 */
	@GetMapping("download/csv")
	public ResponseEntity<Resource> getFile() {
		String filename = "books.csv";
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);
		InputStreamResource file = new InputStreamResource(CSVHelper.booksToCSV(books));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

	/**
	 * This method is used to find all books
	 * 
	 * @param
	 * @return list of books
	 */
	@GetMapping("/allBooks")
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);
		return books;

	}

	/**
	 * This method is used to save the data in to database.
	 * 
	 * @param book
	 * @return
	 */
	@PostMapping("/save")
	public String save(@RequestBody Book book) {
		logger.info("received a request to save ::" + book.getIsbn());
		Book book1 = bookRepository.save(book);
		return book1.getIsbn();
	}

}
