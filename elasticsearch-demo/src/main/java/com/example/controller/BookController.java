package com.example.controller;

import com.example.entity.BookEntity;
import com.example.entity.ESBook;
import com.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * 添加book，这里我直接使用了Entity，为了演示有点不规范！
     */
    @PostMapping("/book")
    public Map<String, String> addBook(@RequestBody BookEntity eSBook) {
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
    public List<BookEntity> test(String key) {
        return bookService.searchBookFromMysql(key);
    }
}