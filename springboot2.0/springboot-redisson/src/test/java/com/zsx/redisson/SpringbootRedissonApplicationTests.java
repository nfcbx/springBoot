package com.zsx.redisson;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedissonApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void test3() {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);

        RLock lock = client.getLock("lock");
        lock.lock(30L, TimeUnit.SECONDS);

//        lock.unlock();

    }

    private String work(){

        return null;
    }


    @Test
    public void test2() {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

//        config.useSingleServer().setPassword("zsx@redis");

        RedissonClient client = Redisson.create(config);

//        RBucket<Object> zhao = client.getBucket("zhao");
//        System.out.println(JSON.toJSONString(zhao)); 报错

        RKeys keys = client.getKeys();
        for (String key : keys.getKeys()) {
            System.out.println(key);
        }

        client.shutdown();
    }


    @Test
    public void test1() {
        Set<String> keys = stringRedisTemplate.keys("*");
        System.out.println(JSON.toJSONString(keys));
    }

    @Test
    public void contextLoads() {
        System.out.println(8899);
    }

    @Before
    public void before() {
        System.out.println();
        System.out.println("before 8899");
        System.out.println();
    }

    @After
    public void after() {
        System.out.println();
        System.out.println("end 8899");
        System.out.println();
    }
}
