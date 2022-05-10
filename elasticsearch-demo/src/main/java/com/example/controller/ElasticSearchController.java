package com.example.controller;

import com.example.service.ElasticHighLevelClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Javen
 * @date 2022/5/10
 */
@Slf4j
@RestController
@RequestMapping("/es")
@RequiredArgsConstructor
public class ElasticSearchController {

    private final ElasticHighLevelClientService elasticHighLevelClientService; // ES底层操作接口

    private final ElasticsearchOperations elasticsearchOperations;

    // Implementation class of RestHighLevelClient and ElasticsearchOperations
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @GetMapping("/createIndex")
    public Boolean createIndex() throws Exception {
        return elasticHighLevelClientService.createIndex("gallen");
    }

    @GetMapping("/deleteIndex")
    public Boolean deleteIndex() throws Exception {
        return elasticHighLevelClientService.deleteIndex("gallen");
    }
}
