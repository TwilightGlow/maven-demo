package com.example.repository;

import com.example.entity.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jinwei Zhang
 * @date 2022/5/7
 */
public interface PostRepository extends ElasticsearchRepository<Post, String> {
}
