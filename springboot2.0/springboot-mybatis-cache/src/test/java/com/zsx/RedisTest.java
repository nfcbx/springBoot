package com.zsx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void test1() {

        String test = stringRedisTemplate.opsForValue().get("test");
        System.out.println(test);

        redisTemplate.opsForValue().set("test1", 1);
    }

    @Test
    void test2() {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {

                Boolean aBoolean = connection.hSet("test".getBytes(), "test".getBytes(), "test".getBytes());
                // 10秒后过期
                connection.expire("test".getBytes(), 10L);
                System.out.println(aBoolean);
                return null;
            }
        });
    }

}
