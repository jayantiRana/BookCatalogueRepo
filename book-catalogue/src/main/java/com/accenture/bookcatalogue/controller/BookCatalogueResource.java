package com.accenture.bookcatalogue.controller;

import com.accenture.bookcatalogue.business.bean.Book;
import com.accenture.bookcatalogue.service.BookServiceImpl;
import com.accenture.bookcatalogue.utils.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("books")
public class BookCatalogueResource {

    @Autowired
   private BookServiceImpl bookService;


   @GetMapping
    public ResponseEntity<Collection<Book>> getBookDetails(){

        Collection<Book> bookList= bookService.getBookDetails();
        if(bookList.isEmpty()){
            throw new BookNotFoundException();
        }
        return new ResponseEntity<Collection<Book>>(bookList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookDetails(@PathVariable ("id") int id){
        Book book = bookService.getDetailsById(id);

        if(book!=null){
            return new ResponseEntity<Book>(book,HttpStatus.OK);
        }else{
            throw new BookNotFoundException();
        }
    }

    @GetMapping("/by")
    public ResponseEntity<Collection<Book>> bookList(@RequestParam(required = false) String title,
                                         @RequestParam(required = false) String author,
                                         @RequestParam(required = false) String isbn){
        Collection<Book> bookList= bookService.getBookList(title,author,isbn);
        if(bookList.isEmpty())
            throw new BookNotFoundException();
        return new ResponseEntity<Collection<Book>>(bookList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book){
    int id =bookService.addBook(book);
    return new ResponseEntity<String>("Book Added successfully with ID:="+id,HttpStatus.CREATED);
    }

   @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
       Book book2= bookService.updateBook(book);
       if(book2==null){
           return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
       }
        return new ResponseEntity<Book>(book2,HttpStatus.CREATED);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable ("id") int id){
        Book book = bookService.deleteBook(id);

        if(book!=null){
            return new ResponseEntity<Book>(book,HttpStatus.OK);
        }else{
            throw new BookNotFoundException();
        }
    }

}
