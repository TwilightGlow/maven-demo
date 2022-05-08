package com.example.service;

import com.example.entity.Book;
import com.example.entity.ESBook;
import com.example.repository.BookRepository;
import com.example.repository.ESBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ESBookRepository esBookRepository;
    private final TransactionTemplate transactionTemplate;

    public void addBook(Book book) {
        final Book saveESBook = transactionTemplate.execute((status) ->
                bookRepository.save(book)
        );
        final ESBook esBook = new ESBook();
        assert saveESBook != null;
        BeanUtils.copyProperties(saveESBook, esBook);
        esBook.setId(saveESBook.getId() + "");
        try {
            esBookRepository.save(esBook);
        } catch (Exception e) {
            log.error(String.format("保存ES错误！%s", e.getMessage()));
        }
    }

    public List<ESBook> search(String keyword) {
//        return esBookRepository.findByTitleOrAuthor(keyword, keyword);
        return esBookRepository.findByTitle(keyword);
    }

    public SearchHits<ESBook> searchBookTitle(String keyword) {
        return esBookRepository.find(keyword);
    }

    public List<Book> searchBookFromMysql(String key) {
        return bookRepository.findByAuthorOrTitle(key, key);
    }
}
