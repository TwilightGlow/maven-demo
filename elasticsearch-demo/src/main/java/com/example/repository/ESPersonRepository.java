package com.example.repository;

import com.example.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Jinwei Zhang
 * @date 2022/5/7
 */
public interface ESPersonRepository extends ElasticsearchRepository<Person,String> {
}
