package com.example.repository;

import com.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, String> {

//    List<Book> findBookByAuthorOrTitle(String author, String title);
    List<Book> findByAuthorOrTitle(String author, String title);
}
