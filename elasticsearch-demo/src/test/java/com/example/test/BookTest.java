package com.example.test;

import com.example.entity.ESBook;
import com.example.repository.ESBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.SearchHits;

/**
 * @author Jinwei Zhang
 * @date 2022/5/7
 */
@SpringBootTest
public class BookTest {

    @Autowired
    private ESBookRepository bookRepository;

    @Test
    public void test() {
        SearchHits<ESBook> kenny = bookRepository.find("kenny");
        System.out.println(kenny);
        System.out.println(kenny.getSearchHits().get(0).getContent());
        System.out.println(kenny.getTotalHitsRelation());
    }
}
