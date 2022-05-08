package com.example.test;

import com.example.entity.Person;
import com.example.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jinwei Zhang
 * @date 2022/5/7
 */
@SpringBootTest
public class ApplicationMainTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void test() {
        System.out.println(personRepository);
        System.out.println(elasticsearchRestTemplate);
    }

    // 创建index
    @Test
    public void createIndex() {
        IndexCoordinates indexCoordinates = IndexCoordinates.of("person");
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(indexCoordinates);
        boolean created = indexOperations.create();
        System.out.println(created);
    }

    @Test
    public void setIndexMappings() {
        IndexCoordinates indexCoordinates = IndexCoordinates.of("person");
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(indexCoordinates);
        boolean created = indexOperations.putMapping(Person.class);
        System.out.println(created);
    }

    @Test
    public void createIndexWithSetSettings() {
        IndexCoordinates indexCoordinates = IndexCoordinates.of("person");
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(indexCoordinates);
        Map<String, Object> settings = new HashMap<>();

        //----------------------------------------静态设置开始----------------------------------------------
        // 静态设置：只能在索引创建时或者在状态为 closed index（闭合的索引）上设置

        //主分片数，默认为5.只能在创建索引时设置，不能修改
        settings.put("index.number_of_shards", 2);

        //是否应在索引打开前检查分片是否损坏，当检查到分片损坏将禁止分片被打开
        settings.put("index.shard.check_on_startup", "false");//默认值
//        settings.put("index.shard.check_on_startup","checksum");//检查物理损坏
//        settings.put("index.shard.check_on_startup","true");//检查物理和逻辑损坏，这将消耗大量内存和CPU
//        settings.put("index.shard.check_on_startup","fix");//检查物理和逻辑损坏。有损坏的分片将被集群自动删除，这可能导致数据丢失

        //自定义路由值可以转发的目的分片数。默认为 1，只能在索引创建时设置。此值必须小于index.number_of_shards
        settings.put("index.routing_partition_size", 1);

        //默认使用LZ4压缩方式存储数据，也可以设置为 best_compression，它使用 DEFLATE 方式以牺牲字段存储性能为代价来获得更高的压缩比例。
        settings.put("index.codec", "best_compression");
        //----------------------------------------静态设置结束----------------------------------------------


        //----------------------------------------动态设置开始----------------------------------------------
        //每个主分片的副本数。默认为 1。
        settings.put("index.number_of_replicas", 0);

        //基于可用节点的数量自动分配副本数量,默认为 false（即禁用此功能）
        settings.put("index.auto_expand_replicas", false);

        //执行刷新操作的频率，这使得索引的最近更改可以被搜索。默认为 1s。可以设置为 -1 以禁用刷新。
        settings.put("index.refresh_interval", "1s");

        //用于索引搜索的 from+size 的最大值。默认为 10000
        settings.put("index.max_result_window", 10000);

        // 在搜索此索引中 rescore 的 window_size 的最大值
        settings.put("index.max_rescore_window", 10000);

        //设置为 true 使索引和索引元数据为只读，false 为允许写入和元数据更改。
        settings.put("index.blocks.read_only", false);

        // 设置为 true 可禁用对索引的读取操作
        settings.put("index.blocks.read", false);

        //设置为 true 可禁用对索引的写入操作。
        settings.put("index.blocks.write", false);

        // 设置为 true 可禁用索引元数据的读取和写入。
        settings.put("index.blocks.metadata", false);

        //索引的每个分片上可用的最大刷新侦听器数
        settings.put("index.max_refresh_listeners", 1000);
        //----------------------------------------动态设置结束----------------------------------------------

        boolean created = indexOperations.create(settings);
        System.out.println(created);
    }

    @Test
    public void deleteIndex() {
        IndexCoordinates indexCoordinates = IndexCoordinates.of("person");
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(indexCoordinates);
        boolean isDeleted = indexOperations.delete();
        System.out.println(isDeleted);
    }

    @Test
    public void saveOne() {
        Person person = new Person();
        person.setId("1");
        person.setName("王天龙");
        person.setAge(30);
        person.setSex("man");
        person.setTel("1111111");
        person.setCreateTime(new Date());

        Person savePerson = personRepository.save(person);

        System.out.println(savePerson);
    }

    @Test
    public void updateOne() {
        Document document = Document.create();
        document.setId("1");
        document.put("name", "天龙战神");

        UpdateQuery.Builder builder = UpdateQuery.builder("1").withDocument(document).withScriptedUpsert(true);

        UpdateResponse updateResponse = elasticsearchRestTemplate.update(builder.build(), IndexCoordinates.of("person"));

        System.out.println(updateResponse.getResult());
    }

    @Test
    public void saveAll() {
        List<Person> personList = new ArrayList<>(3);
        Person person2 = new Person();
        person2.setId("2");
        person2.setName("王天祥");
        person2.setAge(26);
        person2.setSex("男");
        person2.setTel("222222222222");
        person2.setCreateTime(new Date());
        personList.add(person2);

        Person person3 = new Person();
        person3.setId("3");
        person3.setName("王杰");
        person3.setAge(31);
        person3.setSex("女");
        person3.setTel("3333333333");
        person3.setCreateTime(new Date());
        personList.add(person3);
        personRepository.saveAll(personList);
    }

    @Test
    public void searchMatchAll() throws IOException {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder().must(new MatchAllQueryBuilder());
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder);

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Person> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Person.class);

        searchHits.getSearchHits().forEach(personSearchHit -> {
            Person content = personSearchHit.getContent();
            System.out.println(content);
        });
    }


    @Test
    public void searchBoolMustWhere() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", "王天龙"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", 30));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Person> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Person.class);

        searchHits.forEach(personSearchHit -> {
            System.out.println(personSearchHit.getContent());
        });
    }

    @Test
    public void searchShouldWhere() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.matchQuery("name", "天龙"));
        boolQueryBuilder.should(QueryBuilders.matchQuery("age", 26));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Person> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Person.class);
        searchHits.forEach(personSearchHit -> {
            System.out.println(personSearchHit.getContent());
        });
    }

    @Test
    public void searchRange() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gte(26).lt(31);
        boolQueryBuilder.filter(rangeQueryBuilder);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder);
        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Person> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Person.class);
        searchHits.forEach(personSearchHit -> {
            System.out.println(personSearchHit.getContent());
        });
    }


//    /**
//     * 聚合搜索
//     * 聚合搜索，aggs，类似于group by，对age字段进行聚合，
//     */
//    @Test
//    public void aggregations() {
//        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
//                .withAggregations(AggregationBuilders.terms("count").field("sex"))
//                .build();
//
//        SearchHits<Person> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Person.class);
//        //取出聚合结果
//        ElasticsearchAggregations elasticsearchAggregations = (ElasticsearchAggregations) searchHits.getAggregations();
//        Aggregations aggregations = elasticsearchAggregations.aggregations();
//        Terms terms = (Terms) aggregations.asMap().get("count");
//
//        for (Terms.Bucket bucket : terms.getBuckets()) {
//            String keyAsString = bucket.getKeyAsString();   // 聚合字段列的值
//            long docCount = bucket.getDocCount();           // 聚合字段对应的数量
//            System.out.println(keyAsString + " " + docCount);
//        }
//    }

    /**
     * 分页实现
     */
    @Test
    public void searchWithPageable() {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder().must(new MatchAllQueryBuilder());
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                //分页实现
                .withPageable(PageRequest.of(0, 2));

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Person> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Person.class);

        searchHits.getSearchHits().forEach(personSearchHit -> {
            Person content = personSearchHit.getContent();
            System.out.println(content);
        });
    }

//    /**
//     * 排序实现
//     */
//    @Test
//    public void searchWithSort() {
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder().must(new MatchAllQueryBuilder());
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
//                .withQuery(boolQueryBuilder)
//                //分页实现
//                .withPageable(PageRequest.of(0, 10))
//                //排序
//                .withSorts(SortBuilders.fieldSort("createTime").order(SortOrder.DESC), SortBuilders.fieldSort("age").order(SortOrder.DESC));
//
//        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
//        SearchHits<Person> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Person.class);
//
//        searchHits.getSearchHits().forEach(personSearchHit -> {
//            Person content = personSearchHit.getContent();
//            System.out.println(content);
//        });
//    }

    //高并发场景下模拟ES分布式悲观锁的实现
    @Test
    public void highConcurrencyWithPessimisticLock() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        AtomicInteger failedCount = new AtomicInteger(0);

        /**
         * 在testindex未创建的前提下，并发情况下，只能有一个成功，19个失败
         */
        for (int i = 0; i < 20; i++) {
            new Thread(new Task(countDownLatch, failedCount, elasticsearchRestTemplate)).start();
        }

        countDownLatch.countDown();

        TimeUnit.SECONDS.sleep(10);

        System.out.println(failedCount.get());
    }

    private class Task implements Runnable {

        private CountDownLatch countDownLatch;
        private AtomicInteger failedCount;
        private ElasticsearchRestTemplate elasticsearchRestTemplate;

        public Task(CountDownLatch countDownLatch, AtomicInteger failedCount, ElasticsearchRestTemplate elasticsearchRestTemplate) {
            this.countDownLatch = countDownLatch;
            this.failedCount = failedCount;
            this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                IndexCoordinates indexCoordinates = IndexCoordinates.of("testindex");
                IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(indexCoordinates);
                boolean created = indexOperations.create();
                System.out.println(created);
            } catch (Exception e) {
                failedCount.getAndIncrement();
            }
        }
    }
}
