package com.librarymanagement.ms.data;



import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookSpecificationTest {
	
	
	BookSpecification bookSpecification;
	SearchCriteria searchCriteria;
	
	
	@BeforeAll
	void init(){
		searchCriteria  =  new SearchCriteria("isbn", ":", "12345678"); 
		bookSpecification  =  new BookSpecification(searchCriteria);	
	}
	
	@Test
	public void testWithSearchCriteria() throws Exception {	
		Root<Book> root=null;
		CriteriaQuery<?> query =  null;
		CriteriaBuilder builder =CriteriaBuilder.class.newInstance();
		Predicate predicate =bookSpecification.toPredicate(root, query, builder);
		assertNotNull(predicate);	
	}
	
	
	
	

}
