package com.accenture.bookcatalogue.service;

import com.accenture.bookcatalogue.business.bean.Book;
import com.accenture.bookcatalogue.dao.BookDao;
import com.accenture.bookcatalogue.entity.BookEntity;
import com.accenture.bookcatalogue.utils.BookNotFoundException;
import com.accenture.bookcatalogue.utils.IncorrectDataException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BookServiceImpl {
    @Autowired
    private BookDao bookDao;

    public int addBook(Book book){
        BookEntity bookEntity = new BookEntity();
        int isbn= book.getIsbn().length();
        if(book!=null && isbn==13) {
            BeanUtils.copyProperties(book, bookEntity);
        }
        else {
            throw new IncorrectDataException();
        }
        return bookDao.save(bookEntity).getId();
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

    public Book getDetailsById(int id) throws BookNotFoundException {
        Book book = null;
        BookEntity bookEntity = null;
        bookEntity = bookDao.getById(id);
        if (bookEntity != null) {
            book = new Book();
            BeanUtils.copyProperties(bookEntity, book);
        }
        return book;
    }

    public Book deleteBook(int id) throws BookNotFoundException{
        Book book=null;
        if(getDetailsById(id)!=null){
            book=getDetailsById(id);
            BookEntity bookEntity= new BookEntity();
            BeanUtils.copyProperties(book,bookEntity);
          bookDao.delete(bookEntity);
        }
        return book;

    }

    public Book updateBook(Book book) throws BookNotFoundException{
        Book book1= null;
        BookEntity bookEntity= bookDao.getById(book.getId());
        if(bookEntity!=null){
            BeanUtils.copyProperties(book,bookEntity);
            bookDao.save(bookEntity);
            book1=new Book();
            BeanUtils.copyProperties(bookEntity,book1);
        }

        return book1;
    }

    public Collection<Book> getBookList(String title, String author, String isbn) {
        Collection<Book> bookList = new ArrayList<Book>();
        Collection<BookEntity> bookEntityList = null;

        bookEntityList= bookDao.findByIsbnAndTitleAndAuthor(isbn,title,author);
        BeanUtils.copyProperties(bookEntityList,bookList);

        return bookList;
    }
}
