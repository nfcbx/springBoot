package 单元测试.redisDemo;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(RedisTest1.class);

    public ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8, 10, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue());
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
        logger.info("test log ");
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
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        TimeUnit.MILLISECONDS.sleep(1500L);
        threadPool.execute(() -> {
            System.out.println("消费 111 启动");
            new RedisTest1().customer("111");
        });
        threadPool.execute(() -> {
            System.out.println("消费 222 启动");
            new RedisTest1().customer("222");
        });
        threadPool.execute(() -> {
            System.out.println("消费 333 启动");
            customer("333");
        });
        threadPool.execute(() -> {
            System.out.println("消费 444 启动");
            customer("444");
        });
        threadPool.execute(() -> {
            System.out.println("消费 555 启动");
            customer("555");
        });

        boolean flag = true;
        while (flag) {
            sleepThread(4);
            String listflag = RedisClient.getClient().get("listflag");
            if ("3".equals(listflag)) {
                flag = false;
            }
        }

        client.del("listflag");
        logger.info("全部结束");
    }

    public void producer() throws InterruptedException {
        client.set("listflag", "1"); // 标识，1：正在生产，2：生产完毕，3：消费完毕
        for (int i = 0; i < 100; i++) {
//            System.out.println(i);
            String num = String.valueOf(i);
            Long list = client.lpush("list", num);
            System.out.println("生产= " + num + " size= " + list);
//            sleepThread(1);
            TimeUnit.MILLISECONDS.sleep(200L); // 1s create 5 个
        }
        System.out.println("生产完成");
        client.set("listflag", "2");
    }

    public void customer(String name) {
        boolean flag = true;
        while (flag) {
            sleepThread(1);
//            String list = client.rpop("list");
//            List<String> list = client.brpop(50, "list");
            List<String> list = RedisClient.getClient().brpop(2, "list"); // 等待2秒后再获取
            Long size = RedisClient.getClient().llen("list");
            logger.info(name + " 消费= " + JSON.toJSONString(list) + " size= " + size);
            if (size == 0) {
                String listflag = RedisClient.getClient().get("listflag");
                if ("2".equals(listflag) || "3".equals(listflag)) {
                    flag = false;
                    RedisClient.getClient().set("listflag", "3");
                }
            }
        }
        System.out.println(name + " 消费结束");
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
