package com.accenture.bookcatalogue.service;

import com.accenture.bookcatalogue.business.bean.Book;
import com.accenture.bookcatalogue.dao.BookDao;
import com.accenture.bookcatalogue.entity.BookEntity;
import com.accenture.bookcatalogue.utils.BookNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl {
    @Autowired
    private BookDao bookDao;

    public int addBook(Book book){
        BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(book, bookEntity);
        return bookDao.save(bookEntity).getIsbn();
    }

    public Collection<Book> getBookDetails() {
        Collection<BookEntity> collection = null;
        try {
            collection = bookDao.findAll();
        } catch (BookNotFoundException e) {
            System.out.println("Book Details Not Found");
            e.printStackTrace();
        }
        List<Book> bookEntityList = new ArrayList<Book>();
        for (BookEntity bookEntities : collection) {
            Book book = new Book();
            BeanUtils.copyProperties(bookEntities, book);
            bookEntityList.add(book);
        }
        return bookEntityList;
    }

    public Book getDetailsbyId(int isbn) {
        Book book = null;
        BookEntity bookEntity = null;
        try {
            bookEntity = bookDao.getById(isbn);
        } catch (BookNotFoundException e) {
            System.out.println("Book Details Not Found");
            e.printStackTrace();
        }

        if (bookEntity != null) {
            book = new Book();
            BeanUtils.copyProperties(bookEntity, book);
        }
        return book;
    }

    public Book deleteBook(int isbn){
        Book book=null;
        if(getDetailsbyId(isbn)!=null){
            book=getDetailsbyId(isbn);
            BookEntity bookEntity= new BookEntity();
            BeanUtils.copyProperties(book,bookEntity);
          bookDao.delete(bookEntity);
        }
        return book;

    }

    public Book updateBook(Book book) {
        Book book1= null;
        BookEntity bookEntity= bookDao.getById(book.getIsbn());
        if(bookEntity!=null){
            BeanUtils.copyProperties(book,bookEntity);
            bookDao.save(bookEntity);
            book1=new Book();
            BeanUtils.copyProperties(bookEntity,book1);
        }

        return book1;
    }

}
