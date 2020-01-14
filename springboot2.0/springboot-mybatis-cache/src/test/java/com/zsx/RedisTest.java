package com.zsx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void test1() {
        String test = stringRedisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

}
