package com.example.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author Javen
 * @date 2022/4/12
 */
@SpringBootTest
public class RedisListTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testListPush() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
//        Student student = new Student();
//        student.setName("Javen");
//        student.setAge(28);
//        student.setHobby(Arrays.asList("football", "basketball"));
//        opsForList.leftPush("/topic/chat/javen", student);
//        opsForList.leftPush("/topic/chat/javen", student);
//        opsForList.leftPush("/topic/chat/javen", student);
//        opsForList.leftPush("/topic/chat/javen", student);
//        System.out.println(opsForList.size("/topic/chat/javen"));
//        Student student1 = (Student) opsForList.rightPop("/topic/chat/javen");
//        System.out.println(student1);
        System.out.println(opsForList.range("/topic/chat/javen", 0, 10));
    }

    @Test
    public void testIncrement() {
        long count = increment("/topic/chat/count/" + "123", 0);
        long count1 = increment("/topic/chat/count/" + "123", 1);
        long count2 = increment("/topic/chat/count/" + "123", 3);
        System.out.println(count);
        System.out.println(count1);
        System.out.println(count2);
    }

    private long increment(final String key,long delta) {
        try {
            // Avoid the default 'JdkSerializationRedisSerializer' which prompt 'SerializationException'
//            redisTemplate.setValueSerializer(new StringRedisSerializer());
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            return operations.increment(key, delta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
