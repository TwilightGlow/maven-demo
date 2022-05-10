package com.example.repository;

import com.example.entity.Book;
import com.example.model.BookPart;
import com.example.model.BookProjection;
import com.example.model.BookTeacherProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, Long> {

    //    List<Book> findBookByAuthorOrTitle(String author, String title);
    List<Book> findByAuthorOrTitle(String author, String title);

    @Query("select new com.example.model.BookPart(b.title, b.author) " +
            "from Book b where b.id = :id")
    BookPart findBookPartById(@Param("id") Long id);

    @Query("select b.title as title, b.author as author " +
            "from Book b where b.id = :id")
    BookProjection findBookProjectionById(@Param("id") Long id);

    @Query("select b.title as title, t.name as name " +
            "from Book b inner join Teacher t on b.id = t.id where b.id = :id")
    BookTeacherProjection findBookAndTeacher(@Param("id") Long id);
}
