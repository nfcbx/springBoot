package com.zsx.config;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class MybatisRedisCache2 implements Cache {


    private RedisTemplate<String, Object> redisTemplate = SpringContextUtil.getBean("redisTemplate", RedisTemplate.class);


    private final String id;

    public MybatisRedisCache2(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }


    @Override
    public void putObject(Object key, Object value) {
        System.out.println("存入缓存");
        redisTemplate.opsForHash().put(this.id, key.toString(), value);
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("获取缓存");
        return redisTemplate.opsForHash().get(this.id, key.toString());
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println("删除缓存");
        redisTemplate.opsForHash().delete(this.id, key.toString());
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


    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    private final ReadWriteLock readWriteLock = new ReadWriteLock() {

        private Lock lock = new DummyLock();

        @Override
        public Lock readLock() {
            return lock;
        }

        @Override
        public Lock writeLock() {
            return lock;
        }

        class DummyLock implements Lock {

            @Override
            public void lock() {

            }

            @Override
            public void lockInterruptibly() throws InterruptedException {

            }

            @Override
            public boolean tryLock() {
                return false;
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public void unlock() {

            }

            @Override
            public Condition newCondition() {
                return null;
            }
        }
    };
}
