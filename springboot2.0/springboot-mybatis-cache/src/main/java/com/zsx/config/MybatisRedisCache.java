package com.zsx.config;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

public class MybatisRedisCache implements Cache {


    private RedisTemplate<String, Object> redisTemplate = SpringContextUtil.getBean("redisTemplate", RedisTemplate.class);


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
        System.out.println("存入缓存");
        redisTemplate.opsForHash().put(this.id, key, value);
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("获取缓存");
        return redisTemplate.opsForHash().get(this.id, key);
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println("删除缓存");
        redisTemplate.opsForHash().delete(this.id, key);
        return null;
    }

    @Override
    public void clear() {
        System.out.println("清空缓存");
        redisTemplate.delete(this.id);
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.opsForHash().size(this.id);
        System.out.println("获取缓存size : " + size);
        return size.intValue();
    }
}
