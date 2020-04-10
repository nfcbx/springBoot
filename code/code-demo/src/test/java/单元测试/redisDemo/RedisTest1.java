package 单元测试.redisDemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisTest1 {

    public Jedis client;

    @Before
    public void 开始() {
        client = RedisClient.getClient();
    }

    @After
    public void 结束() {
        client.close();
    }

    public void main(String[] args) {
        test1();
    }

    @Test
    public void test1() {
        String test = client.get("test");
        System.out.println(test);
    }


}
