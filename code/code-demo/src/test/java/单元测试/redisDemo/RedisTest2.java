package 单元测试.redisDemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RedisTest2 {

    public Jedis client;

    @Before
    public void 开始() {
        client = RedisClient.getClient();
    }

    @After
    public void 结束() {
        client.close();
    }

    @Test
    public void test1() throws InterruptedException {
        int i = 0;
        while (true) {
            System.out.println(++i);
            TimeUnit.SECONDS.sleep(1L);

            String zhao = client.get("zhao");
            Long time = client.ttl("zhao");

            System.out.println(zhao + " 过期时间 " + time);
            System.out.println();
        }

    }


    @Test(timeout = 2L)
    public void test2() {

        // 开始时间
        long startTime = System.currentTimeMillis();

        while ((startTime + 10000) > System.currentTimeMillis()) {

            int i = new Random().nextInt(20);
            System.out.println(i);
            if (i == 10) {
                return;
            }

        }

        System.out.println("结束了");

    }

}
