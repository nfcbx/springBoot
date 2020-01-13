package com.zsx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;


@SpringBootTest
class AppTests {

	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Test
	void contextLoads() {
		System.out.println(8899);
		System.out.println(redisTemplate);

		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();


		operations.set("name", "asdf");

		Set keys = redisTemplate.keys("*");
		for (Object key : keys) {
			System.out.println(key);
		}
	}


}
