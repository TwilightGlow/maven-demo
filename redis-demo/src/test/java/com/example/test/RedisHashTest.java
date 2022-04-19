package com.example.test;

import com.example.model.Student;
import com.example.model.Teacher;
import com.example.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javen
 * @date 2022/4/7
 */
@SpringBootTest
public class RedisHashTest {

    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    private static final String prefix = "test:hash:";

    @Test
    public void testHash() {
        HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
        List<String> hobbys = new ArrayList<>();
        hobbys.add("1");
        hobbys.add("2");
//        opsForHash.put(prefix + "123", "Javen", new Student("Javen", 28, Arrays.asList("1", "3")));
//        opsForHash.put(prefix + "123", "Sophia", new Student("Sophia", 27, hobbys));
//        opsForHash.put(prefix + "123", "Gallen", new Student("Gallen", 26, hobbys));
        Student javen = (Student) opsForHash.get(prefix + "123", "Javen");
        System.out.println(javen.getName());
        System.out.println(javen.getAge());
        System.out.println(javen.getHobby());
    }

    @Test
    public void testHashPipeline() {
        Teacher javen = new Teacher("Javen", 28);
        Teacher gallen = new Teacher("Gallen", 26);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(javen);
        teachers.add(gallen);
        System.out.println(redisUtils.setHashInPipeline(teachers));
//        List<String> strings = new ArrayList<>();
//        strings.add("Javen");
//        strings.add("Gallen");
//        ZSetOperations<String, String> opsForZSet = redisTemplate.opsForZSet();
//        Set<String> range = opsForZSet.range("test:zset:789", 0, 100);
//        System.out.println(range);
//        System.out.println(redisUtils.getZsetInPipeline(strings));
////        List<Teacher> teachers = redisUtils.getHashInPipeline(strings);
////        System.out.println(teachers);
//        List<Boolean> results = redisUtils.deleteHashInPipeline(strings);
//        System.out.println(results);
    }
}
