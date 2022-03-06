package com.accenture.bookcatalogue.dao;

import com.accenture.bookcatalogue.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookDao extends JpaRepository<BookEntity, Integer>
{
    Collection<BookEntity> findByIsbnAndTitleAndAuthor(String isbn,String title,String author);

}
