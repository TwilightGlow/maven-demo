package com.example.repository;

import com.example.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findBookByAuthorOrTitle(String author,String title);
}
