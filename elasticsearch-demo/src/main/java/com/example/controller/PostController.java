package com.example.controller;

import com.example.entity.Post;
import com.example.repository.ESPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    private final ESPostRepository ESPostRepository;

    /**
     * 单字符串模糊查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集
     */
    @RequestMapping("/singleWord")
    public Iterable<Post> singleTitle(@PageableDefault Pageable pageable) {
        //使用queryStringQuery完成单字符串查询
        return ESPostRepository.findAll(pageable);
    }

    @GetMapping("/post/saveAll")
    public String saveAll() {
        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        post.setId("abcde");
        post.setContent("456");
        posts.add(post);
        posts.add(post);
        posts.add(post);
        posts.add(post);
        posts.add(post);
        ESPostRepository.saveAll(posts);
        return "1";
    }
}
