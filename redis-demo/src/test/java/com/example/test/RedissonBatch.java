package com.example.test;

import com.example.model.Student;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBatch;
import org.redisson.api.RMap;
import org.redisson.api.RMapAsync;
import org.redisson.api.RScoredSortedSetAsync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Javen
 * @date 2022/4/8
 */
@SpringBootTest
public class RedissonBatch {

    @Autowired
    private Redisson redisson;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testBatch() {
        RBatch batch = redisson.createBatch();
        RScoredSortedSetAsync<Object> scoredSortedSet = batch.getScoredSortedSet("mytest:zset:123");
        RMapAsync<Object, Student> map = batch.getMap("mytest:hash:123");
        scoredSortedSet.addAsync(10, 1);
        scoredSortedSet.addAsync(20, "2");
        scoredSortedSet.addAsync(30, "3");
//        scoredSortedSet.addAsync(3, 3L);
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
//        map.fastPutAsync("Javen", new Student("Javen", 28, strings));
//        map.fastPutAsync("Gallen", new Student("Gallen", 28, strings));
//        map.fastPutAsync(1, new Student("Gallen", 28, strings));
//        map.fastPutAsync("2", new Student("Gallen", 28, strings));
//        map.fastPutAsync(3L, new Student("Gallen", 28, strings));
//        BatchResult<?> execute = batch.execute();
//        for (Object respons : execute.getResponses()) {
//            System.out.println(respons);
//        }
        List<Boolean> responses = (List<Boolean>) batch.execute().getResponses();
        System.out.println(responses);
    }

    @Test
    public void test() {
//        ZSetOperations<String, Object> opsForZSet = redisTemplate.opsForZSet();
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();
        System.out.println(opsForZSet.zCard("mytest:zset:123"));

        Set<String> objects = opsForZSet.reverseRange("mytest:zset:123", 0, 100);
        System.out.println(objects);
//        System.out.println(objects.stream().mapToLong(Long::parseLong).parallel().max().getAsLong());
//        System.out.println(opsForZSet.range("mytest:zset:123", 0, 100));
//        System.out.println(redisTemplate.opsForZSet().zCard("mytest:zset:123"));
//        System.out.println(redisTemplate.opsForZSet().count("mytest:zset:123", 0, 100));
//        System.out.println(reverseRange("mytest:zset:123", 0, 100));
    }

    @Test
    public void test1() {
//        RMap<Object, Object> map = redisson.getMap("mytest:hash:123");
//        Student javen = (Student) map.get("Javen");
//        System.out.println(javen);
//        Student gallen = (Student) map.get("Gallen");
//        System.out.println(gallen);
//        HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
//        Student javen = (Student) opsForHash.get("mytest:hash:123", "Javen");
//        System.out.println(javen);
        RMap<Object, Student> map = redisson.getMap("mytest:hash:123");
        Student javen = map.get(1);
        System.out.println(javen);
    }

    @Test
    public void test2() {
        Boolean delete1 = redisTemplate.delete("mytest:hash:123");
        Boolean delete2 = redisTemplate.delete("mytest:zset:123");
        System.out.println(delete1);
        System.out.println(delete2);
    }

    private Set<Object> reverseRange(String key, long start, long end){
        // Avoid the default 'JdkSerializationRedisSerializer' which prompt 'SerializationException'
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.reverseRange(key, start, end);
    }
}
