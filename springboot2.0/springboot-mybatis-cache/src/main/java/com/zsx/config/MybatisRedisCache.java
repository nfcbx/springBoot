package com.zsx.config;

import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class MybatisRedisCache implements Cache {

    @Autowired
    private static RedisTemplate<String, Object> redisTemplate;


    private final String id;

    public MybatisRedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {

    }

    @Override
    public Object getObject(Object key) {

        System.out.println(redisTemplate);
        System.out.println(redisTemplate.hasKey("name"));
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
