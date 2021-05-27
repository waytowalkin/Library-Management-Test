package com.librarymanagement.ms.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;




public class BookTest {
	
	@Test
	public void successFullBookCreationRequest(){
		Book b =  new Book();
        b.setIsbn("456789012");
        b.setAuthor("Joe");
        b.setTitle("Finance");
        b.setTag("Finance");
        assertEquals("456789012", b.getIsbn());
	}

}
