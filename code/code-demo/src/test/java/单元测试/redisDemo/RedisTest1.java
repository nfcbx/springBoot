package 单元测试.redisDemo;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import 多线程.CallableDemo1;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RedisTest1 {

    public ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 2, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue());
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
    public void test4() throws InterruptedException {
        threadPool.execute(() -> {
            producer();
        });
        TimeUnit.MILLISECONDS.sleep(1500L);
        threadPool.execute(() -> {
            customer();
        });
        while (true) {
        }
    }

    public void producer() {
        for (int i = 0; i < 50; i++) {
            System.out.println(i);
            String num = String.valueOf(i);
            Long list = client.lpush("list", num);
            System.out.println("生产= " + num + " size= " + list);
            sleepThread(1);
        }
    }

    public void customer() {
        int i = 0;
        while (i < 5) {
//            sleepThread(1);
//            String list = client.rpop("list");
//            List<String> list = client.brpop(50, "list");
            List<String> list = RedisClient.getClient().brpop(5, "list");
            Long size = RedisClient.getClient().llen("list");
            System.out.println("消费= " + JSON.toJSONString(list) + " size= " + size);
//            if (size == 0) {
//                sleepThread(1);
//                i++;
//            }
        }
    }


    private static int getRandom() {
        int i = new Random().nextInt(3);
        return i;
    }

    public static void sleepThread(Integer timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepThread() {
        sleepThread(getRandom());
    }


    @Test
    public void test22() {
        CompletableFuture.runAsync(() -> {
            _50w();
        });
        CompletableFuture.runAsync(() -> {
            _50wpop();
        });
        while (true) {

        }
    }

    @Test
    public void _50w() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
//            client.lpush("biglist", UUID.randomUUID().toString());
            client.lpush("biglist", String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000);
    }

    @Test
    public void _50wpop() {
        while (true) {
            String list = client.rpop("biglist");
            System.out.println("消费= " + list);
            if (list == null) {
                sleepThread(1);
            }
        }
    }
}
