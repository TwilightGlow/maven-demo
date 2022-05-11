package com.example.controller;

import com.example.entity.Book;
import com.example.entity.ESBook;
import com.example.model.BookPart;
import com.example.model.BookProjection;
import com.example.model.BookTeacherProjection;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookRepository bookRepository;

    @PostMapping("/book")
    public Map<String, String> addBook(@RequestBody Book eSBook) {
        bookService.addBook(eSBook);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "ok");
        return map;
    }

    /**
     * 从ES中搜索
     */
    @GetMapping("/book/search/es")
    public List<ESBook> searchES(String key) {
        return bookService.search(key);
    }

    @GetMapping("/book/search")
    public SearchHits<ESBook> search(String key) {
        return bookService.searchBookTitle(key);
    }

    @GetMapping("/book/search/mysql")
    public List<Book> searchMysql(String key) {
        return bookService.searchBookFromMysql(key);
    }

    @GetMapping("/book/search/mysql/async")
    public List<Book> searchMysqlAsync(String key) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Book>> byAuthor = bookRepository.findByAuthor(key);
        return byAuthor.get();
    }

    @GetMapping("/book/search/mysql/deleteByIdBatch")
    public Boolean deleteByIdBatch() {
        bookRepository.deleteAllByIdInBatch(Arrays.asList(1L, 2L));
//        bookRepository.deleteAllById(Arrays.asList(1, 2));
        return true;
    }

    @GetMapping("/book/search/mysql/save")
    public Boolean saveAndFlush() {
        Book book = new Book();
        book.setId(1L);
        book.setAuthor("kenny");
        book.setMoney(new BigDecimal("123.45"));
        book.setPrice(15.0);
        book.setTitle("kenny");
        book.setCreateTime(new Date());
        bookRepository.saveAndFlush(book);
        return true;
    }

    @GetMapping("/bookPart")
    public BookPart searchBookPart() {
        BookPart bookPartById = bookRepository.findBookPartById(1L);
        return bookPartById;
    }

    @GetMapping("/bookProjection")
    public String searchBookProjection() {
        BookProjection bookPartById = bookRepository.findBookProjectionById(1L);
        return bookPartById.getDescription();
    }

    @GetMapping("/bookJoin")
    public String bookJoin() {
        BookTeacherProjection bookAndTeacher = bookRepository.findBookAndTeacher(1L);
        return bookAndTeacher.getTitle();
    }
}