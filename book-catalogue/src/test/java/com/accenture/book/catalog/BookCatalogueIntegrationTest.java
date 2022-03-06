package com.accenture.book.catalog;

import com.accenture.bookcatalogue.BookCatalogueApplication;
import com.accenture.bookcatalogue.business.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = BookCatalogueApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookCatalogueIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddBook() {

        Book book1 = new Book();
        book1.setId(1);
        book1.setAuthor("ABC");
        book1.setTitle("XYZ");
        book1.setIsbn("");
        book1.setPublishDate(new Date());
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/book/catalogue/addBook", book1, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}

