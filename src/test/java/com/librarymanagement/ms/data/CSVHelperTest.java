package com.librarymanagement.ms.data;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CSVHelperTest {

	@Test
	public void successfullcsvformatwithcdRequest() {
		Book b = new Book();
		b.setIsbn("456789012");
		b.setAuthor("Joe");
		b.setTitle("Finance");
		b.setTag("Finance");
		Book b2 = new Book();
		b2.setIsbn("456789012");
		b2.setAuthor("Joe");
		b2.setTitle("Finance");
		b2.setTag("Finance");
		List<Book> books = new ArrayList<>();
		books.add(b);
		books.add(b2);
		ByteArrayInputStream inputStream = CSVHelper.booksToCSV(books);
		assertNotNull(inputStream);
	}

}
