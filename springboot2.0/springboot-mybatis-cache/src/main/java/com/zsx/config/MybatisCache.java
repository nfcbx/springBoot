package com.zsx.config;

import org.apache.ibatis.cache.Cache;

public class MybatisCache implements Cache {

    private final String id;

    public MybatisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println(key);
        System.out.println(value);
    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
        System.out.println("clearclearclearclearclearclearclearclearclearclearclear");
    }

    @Override
    public int getSize() {
        return 0;
    }
}
