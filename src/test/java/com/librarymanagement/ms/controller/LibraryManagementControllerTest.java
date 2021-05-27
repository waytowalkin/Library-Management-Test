package com.librarymanagement.ms.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.librarymanagement.ms.BaseTestClass;
import com.librarymanagement.ms.data.Book;
import com.librarymanagement.ms.data.BookSpecificationBuilder;
import com.librarymanagement.ms.repository.BookRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class LibraryManagementControllerTest extends BaseTestClass {

	@Autowired
	private MockMvc mocMvc;

	private static ObjectMapper objectMapper;

	@MockBean
	private BookRepository bookRepository;

	@BeforeAll
	public static void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

	@Test
	public void testSuccessfullBookwithSave() throws Exception {

		Mockito.when(bookRepository.save(createRequest())).thenReturn(createResponse());
		mocMvc.perform(MockMvcRequestBuilders.post(LibraryManagementController.PATH + "\\save")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(createRequest())))
				.andExpect(jsonPath("isbn").value(createResponse().getIsbn()))
				.andExpect(jsonPath("title").value(createResponse().getTitle()));
		Mockito.verify(bookRepository).save(createRequest());
	}

	@Test
	public void testSuccesssearchWithIsbn() throws Exception {
		Mockito.when(bookRepository.findById("12345678")).thenReturn(Optional.of(createResponse()));
		mocMvc.perform(MockMvcRequestBuilders.get(LibraryManagementController.PATH + "\\isbn")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString("12345678")))
		        .andExpect(jsonPath("isbn").value(createResponse().getIsbn()));
		Mockito.verify(bookRepository).findById("12345678");
	}

	@Test
	public void testSuccessfullBookwithSearch() throws Exception {
		Book response = createResponse();
		BookSpecificationBuilder builder = new BookSpecificationBuilder();
		Specification<Book> spec = builder.build();
		Mockito.when(bookRepository.findAll(spec)).thenReturn(Arrays.asList(response));
		mocMvc.perform(MockMvcRequestBuilders.post(LibraryManagementController.PATH + "\\search")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString("isbn:12345678")))
				.andExpect((ResultMatcher) status());
		Mockito.verify(bookRepository).findAll(spec);
	}

	@Test
	public void testSuccessfullDownloadAllFiles() throws Exception {

		Book b = new Book();
		b.setIsbn("12345678");
		b.setAuthor("James");
		b.setTitle("Java");

		Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(b));
		mocMvc.perform(MockMvcRequestBuilders.get(LibraryManagementController.PATH + "\\download/csv")
				.contentType(MediaType.APPLICATION_JSON)).andExpect((ResultMatcher) status());
		Mockito.verify(bookRepository).findAll();
	}

	private Object status() {
		return "200";
	}

}
