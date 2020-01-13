package com.zsx;

import com.alibaba.fastjson.JSON;
import com.zsx.dao.UserDao;
import com.zsx.entity.User;
import com.zsx.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@SpringBootTest
class AppTests {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserDao userDao;
    @Autowired
    private UserService userService;

    @Test
    void test1() {
        List<User> all = userService.getAll();
        System.out.println(8899);
        System.out.println(JSON.toJSONString(all));

        System.out.println();


        List<User> all2 = userService.getAll();
        System.out.println(8899);
        System.out.println(JSON.toJSONString(all2));
    }

    @Test
    @Transactional // 开启事务，一级缓存才生效
    void test2() {
        List<User> users = userDao.selectAll();
        System.out.println(8899);
        System.out.println(JSON.toJSONString(users));

        System.out.println();

        List<User> users2 = userDao.selectAll();
        System.out.println(8899);
        System.out.println(JSON.toJSONString(users2));
    }

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
