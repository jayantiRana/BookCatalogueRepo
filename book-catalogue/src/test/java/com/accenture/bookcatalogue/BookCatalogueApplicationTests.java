package com.accenture.bookcatalogue;

import com.accenture.bookcatalogue.business.bean.Book;
import com.accenture.bookcatalogue.entity.BookEntity;
import com.accenture.bookcatalogue.service.BookServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookCatalogueApplicationTests {

	private static final String ERROR="Error";
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private BookServiceImpl bookService;
	private Book book;
	private String title="ABC";
	private String author="XYZ";
	private int isbn=123;
	private LocalDate publishDate=LocalDate.of(2021,01,01);
	private ObjectMapper mapper= new ObjectMapper();

	@BeforeEach
	public void setup(){
		//createmo
		BookEntity bookEntity1= new BookEntity();
		bookEntity1.setAuthor("ABC");
		bookEntity1.setTitle("XYZ");
		bookEntity1.setIsbn(12345);
		bookEntity1.setPublishDate(new Date());

		Book book1= new Book();
		book1.setAuthor("ABC");
		book1.setTitle("XYZ");
		book1.setIsbn(12345);
		book1.setPublishDate(new Date());
		Mockito.when(bookService.addBook(book1)).thenReturn(12345);
		Mockito.when(bookService.deleteBook(12345)).thenReturn(book1);
		Mockito.when(bookService.updateBook(book1)).thenReturn(book1);
		//mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext) ).build();

	}

	@Test
	public void testGetDetails() throws Exception {
		mockMvc.perform(get("/book/catalogue/getDetails")).andExpect(status().isOk());
	}

	@Test
	public void testAddBook() throws Exception {
		String json= mapper.writeValueAsString(book);
		mockMvc.perform(post("/book/catalogue/addBook").contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

	@Test
	public void testAddBookFail() throws Exception {
		String json= mapper.writeValueAsString(book);
		mockMvc.perform(post("/book/catalogue/addBook")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")
				.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
