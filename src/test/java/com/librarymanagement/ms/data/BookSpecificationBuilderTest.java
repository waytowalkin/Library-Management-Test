package com.librarymanagement.ms.data;



import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecificationBuilderTest {
	
	static BookSpecificationBuilder bookSpecificationBuilder = null;
	static SearchCriteria  searchCriteria = null;
	
	@BeforeAll
	static void init(){
		bookSpecificationBuilder  =  new BookSpecificationBuilder();	
		searchCriteria  =  new SearchCriteria("isbn", ":", "12345678");
	}
	
	@Test
	public void testWithSearchCriteria() {
		
		bookSpecificationBuilder.with("isbn", ":", "123456789");
		Specification<Book> bookSpec  = bookSpecificationBuilder.build();
		assertNotNull(bookSpec);	
	}

}
