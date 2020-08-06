package 单元测试.redisDemo;

import com.google.common.collect.Sets;
import redis.clients.jedis.*;

import java.util.Set;

public class RedisClient {

    // 集群模式，需要redis也开启集群配置
    public static JedisCluster getCluster() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(100);// 最大空闲数
        config.setMaxTotal(1000);// 最大连接数
        config.setMaxWaitMillis(10000);// 超时时间
        config.setTestOnBorrow(true);

        Set<HostAndPort> nodes = Sets.newHashSet();
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        JedisCluster cluster = new JedisCluster(nodes, config);
        return cluster;
    }


    public static Jedis getClient() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(100);
        config.setMaxTotal(1000);
        config.setMaxWaitMillis(10 * 1000);
        config.setTestOnBorrow(true);

        Jedis resource = new JedisPool(config, "127.0.0.1", 6379).getResource();
        System.out.println("redis 初始化成功");
        return resource;
    }


}
