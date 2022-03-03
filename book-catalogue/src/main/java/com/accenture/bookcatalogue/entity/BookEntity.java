package com.accenture.bookcatalogue.entity;

import com.accenture.bookcatalogue.business.bean.Book;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="book")
public class BookEntity {

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;
    @Id
    @Column(name="isbn")
    private int isbn;

    @Column(name="publishDate")
    private Date publishDate;

    public BookEntity(){
        super();
    }

    public BookEntity (String title, String author, int isbn, Date publishDate){
        super();
        this.author=author;
        this.isbn=isbn;
        this.publishDate=publishDate;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }
}
