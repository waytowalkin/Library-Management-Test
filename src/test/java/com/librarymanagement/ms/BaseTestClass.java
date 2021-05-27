package com.librarymanagement.ms;

import com.librarymanagement.ms.data.Book;

public class BaseTestClass {
	
	public Book createRequest(){
		Book b =  new Book();
		b.setIsbn("12345678");
		b.setAuthor("James");
		b.setTitle("Java");
		
		return b;		
	}
	
	
	public Book createResponse(){
		Book b =  new Book();
		b.setIsbn("12345678");
		b.setAuthor("James");
		b.setTitle("Java");
		
		return b;		
	}

}
