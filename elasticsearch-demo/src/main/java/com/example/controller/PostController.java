package com.example.controller;

import com.example.entity.Post;
import com.example.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jinwei Zhang
 * @date 2022/5/7
 */
@RestController
@RequiredArgsConstructor
public class PostController {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    private final ElasticsearchOperations elasticsearchOperations;

    private final PostRepository postRepository;

    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     */
    @RequestMapping("/singleWord")
    public Iterable<Post> singleTitle(@PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        return postRepository.findAll(pageable);
    }
}
