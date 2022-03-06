//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.accenture.book.catalog;

import com.accenture.bookcatalogue.BookCatalogueApplication;
import com.accenture.bookcatalogue.business.bean.Book;
import com.accenture.bookcatalogue.entity.BookEntity;
import com.accenture.bookcatalogue.service.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = BookCatalogueApplication.class)
class BookCatalogueApplicationTests {
    private static final String ERROR = "Error";
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private BookServiceImpl bookService;
    private Book book;
    private int id=1;
    private String title = "abc";
    private String author = "XYZ";
    private String isbn = "123456789qwer";
    private LocalDate publishDate = LocalDate.of(2021, 1, 1);
    private ObjectMapper mapper = new ObjectMapper();

    BookCatalogueApplicationTests() {
    }

    @BeforeEach
    public void setup() {
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthor("ABC");
        bookEntity1.setId(1);
        bookEntity1.setTitle("XYZ");
        bookEntity1.setIsbn("12345");
        bookEntity1.setPublishDate(new Date());
        Book book1 = new Book();
        book1.setAuthor("ABC");
        book1.setTitle("XYZ");
        book1.setIsbn("1122334455661");
        book1.setPublishDate(new Date());
        Mockito.when(bookService.addBook(book1)).thenReturn(1);
        Mockito.when(bookService.deleteBook(1)).thenReturn(book1);
        Mockito.when(bookService.updateBook(book1)).thenReturn(book1);
    }

    @Test
    public void testGetDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book/catalogue/getDetails", book))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddBook() throws Exception {
        String json =mapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders.post("/book/catalogue/addBook", book)
                        .contentType("application/json").characterEncoding("utf-8").content(json)
                        .accept(new MediaType[]{MediaType.APPLICATION_JSON}))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testAddBookFail() throws Exception {
        String json = mapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders.post("/book/catalogue/addBook", book)
                        .contentType("application/json").characterEncoding("utf-8").content(json)
                        .accept(new MediaType[]{MediaType.APPLICATION_JSON}))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
