//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.accenture.book.catalog;

import com.accenture.bookcatalogue.BookCatalogueApplication;
import com.accenture.bookcatalogue.business.bean.Book;
import com.accenture.bookcatalogue.service.BookServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = BookCatalogueApplication.class)
public class BookServiceTest extends BookServiceImpl {
    private BookServiceImpl bookService = (BookServiceImpl)Mockito.mock(BookServiceImpl.class);
    Book book1;
    Collection<Book> booklist = new ArrayList();

    public BookServiceTest() {
    }

    @BeforeEach
    public void setup() {
        book1 = new Book();
        book1.setAuthor("ABC");
        book1.setTitle("XYZ");
        book1.setIsbn("12345");
        book1.setPublishDate(new Date());
        Book book2 = new Book();
        book2.setAuthor("ABC");
        book2.setTitle("XYZ");
        book2.setIsbn("12345");
        book2.setPublishDate(new Date());
        booklist.add(book1);
        booklist.add(book2);
        Mockito.when(bookService.addBook(book1)).thenReturn(12345);
        Mockito.when(bookService.updateBook(book1)).thenReturn(book1);
        Mockito.when(bookService.deleteBook(12345)).thenReturn(book1);
        Mockito.when(bookService.getDetailsById(12345)).thenReturn(book1);
        Mockito.when(bookService.getBookDetails()).thenReturn(booklist);
    }

    @Test
    public void testAdd() {
        int bookIsbn = bookService.addBook(book1);
        Assertions.assertEquals(bookIsbn, 12345);
    }

    @Test
    public void testUpdate() {
        Book book = bookService.updateBook(book1);
        Assertions.assertEquals(book1, book);
    }

    @Test
    public void testDelete() {
        Book book = bookService.deleteBook(12345);
        Assertions.assertEquals(book1, book);
    }

    @Test
    public void testGetDetails() {
        Collection<Book> book = bookService.getBookDetails();
        Assertions.assertEquals(booklist, book);
    }

}
