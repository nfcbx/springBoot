## 一级缓存
默认开启，但需要在同一个事务里面才生效，方法外边添加注解@Transactional即可。


## mybatis开启二级缓存

1、全局配置
<setting name="cacheEnabled" value="true" />

2、在Mybatis的映射XML中配置cache或者 cache-ref
<cache/>


> Mybatis二级缓存默认采用的org.apache.ibatis.cache.impl.PerpetualCache实现的


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

## <cache/>说明

- type 
默认是org.apache.ibatis.cache.impl.PerpetualCache，只需要实现 org.apache.ibatis.cache.Cache接口即可。

- eviction  
淘汰算法：LRU、FIFO、Scheduled

- flushInterval
缓存时间，以毫秒为单位

- size缓存大小

- readOnly如果为false的话，缓存对象必须是可序列化的



















---