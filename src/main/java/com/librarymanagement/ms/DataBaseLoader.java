package com.librarymanagement.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.librarymanagement.ms.data.Book;
import com.librarymanagement.ms.repository.BookRepository;

@Component
public class DataBaseLoader implements CommandLineRunner {

	@Autowired
	private final BookRepository bookRepository;

	@Autowired
	public DataBaseLoader(BookRepository bookRepository) {
		this.bookRepository = bookRepository;

	}

	@Override
	public void run(String... args) throws Exception {
		
		Book book1 = new Book();
        book1.setIsbn("12345678");
        book1.setAuthor("James");
        book1.setTitle("Java");
        book1.setTag("Java");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setIsbn("4325342656");
        book2.setAuthor("Doe");
        book2.setTitle("DotNet");
        book2.setTag("DotNet");
        bookRepository.save(book2);
        
        
        Book book3 = new Book();
        book3.setIsbn("456789012");
        book3.setAuthor("Joe");
        book3.setTitle("Finance");
        book3.setTag("Finance");
        bookRepository.save(book3);

	}

}
