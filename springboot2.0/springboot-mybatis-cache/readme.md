




## mybatis开启二级缓存

1、全局配置
<setting name="cacheEnabled" value="true" />

2、在Mybatis的映射XML中配置cache或者 cache-ref
<cache/>


## 使用redis做分布式缓存

1、添加依赖
```
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-redis</artifactId>
    <version>1.0.0-beta2</version>
</dependency>
```

2、开启全局缓存cacheEnabled=true
3、mapper.xml里面添加
<cache type="org.mybatis.caches.redis.RedisCache" />




















---