package 单元测试.redisDemo;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import 多线程.CallableDemo1;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RedisTest1 {

    public ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue(100));
    public Jedis client;

    @Before
    public void 开始() {
        client = RedisClient.getClient();
    }

    @After
    public void 结束() {
        client.close();
    }

    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            RedisProducer.main(null);
        });
        CompletableFuture.runAsync(() -> {
            RedisCustomer.main(null);
        });
        while (true) {

        }
    }

    @Test
    public void test1() {
        String test = client.get("test");
        System.out.println(test);
    }

    @Test
    public void test2() {
        client.lpush("list", "1");
        client.lpush("list", "2");
        client.lpush("list", "3");
        client.lpush("list", "4");
        client.lpush("list", "4");
        Long size = client.lpush("list", "4");
        System.out.println("集合大小：" + size);
    }


    @Test
    public void test3() {
        String value = client.rpop("list");
        System.out.println("取出：" + value);
        Long size = client.llen("list");
        System.out.println("还剩：" + size);
    }

    @Test
    public void test4() {
        for (int i = 1; i <= 10; i++) {
            producer(i);
        }
        customer();

        while (true) {
        }
    }

    public void producer(int i) {
        System.out.println(i);
        String num = String.valueOf(i);
        CompletableFuture.runAsync(() -> {
            Long list = client.lpush("list", num);
            System.out.println("生产= " + num + " size= " + list);
            sleepThread();
        });
    }

    public void customer() {
        while (true) {
            sleepThread();
            String list = client.rpop("list");
            Long size = client.llen("list");
            System.out.println("消费= " + list + " size= " + size);
            if (size == 0) {
                sleepThread();
            }
        }
    }


    private static int getRandom() {
        int i = new Random().nextInt(3);
        return i;
    }

    public static void sleepThread() {
        try {
            TimeUnit.SECONDS.sleep(getRandom());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
