package com.accenture.bookcatalogue.dao;

import com.accenture.bookcatalogue.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<BookEntity, Integer>
{


}
