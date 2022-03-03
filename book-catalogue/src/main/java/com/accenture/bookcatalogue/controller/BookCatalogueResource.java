package com.accenture.bookcatalogue.controller;

import com.accenture.bookcatalogue.business.bean.Book;
import com.accenture.bookcatalogue.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BookCatalogueResource {

    @Autowired
   private BookServiceImpl bookService;

    @RequestMapping(value = "book/catalogue/getDetails", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Book>> getBookDetails(){

        Collection<Book> bookList= bookService.getBookDetails();
        System.out.println("*********Book Details*******\n"+bookList);
        return new ResponseEntity<Collection<Book>>(bookList, HttpStatus.OK);
    }

    @RequestMapping(value = "book/catalogue/getDetailsById/{isbn}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBookDetailsById(@PathVariable ("isbn") int isbn){
        Book book = bookService.getDetailsbyId(isbn);

        if(book!=null){
            return new ResponseEntity<Book>(book,HttpStatus.OK);
        }else{
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "book/catalogue/addBook", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addBook(@RequestBody Book book){
    int isbn =bookService.addBook(book);
    return new ResponseEntity<String>("Book Added successfully with ISBN:="+isbn,HttpStatus.CREATED);
    }

    @RequestMapping(value = "book/catalogue/updateBook", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
       Book book2= bookService.updateBook(book);
       if(book2==null){
           return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
       }
        return new ResponseEntity<Book>(book2,HttpStatus.CREATED);
    }

    @RequestMapping(value = "book/catalogue/delete/{isbn}", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> deleteBook(@PathVariable ("isbn") int isbn){
        Book book = bookService.deleteBook(isbn);

        if(book!=null){
            return new ResponseEntity<Book>(book,HttpStatus.OK);
        }else{
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

}
