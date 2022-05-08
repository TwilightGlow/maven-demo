package com.example.repository;

import com.example.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jinwei Zhang
 * @date 2022/5/7
 */
public interface PersonRepository extends ElasticsearchRepository<Person,String> {
}
