package 多线程.自旋锁;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import 单元测试.redisDemo.RedisClient;

import javax.sound.midi.Soundbank;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

public class MainTest {


    private Random random = new Random();

    public static void main(String[] args) {

        CompletableFuture.runAsync(() -> {

            LockTest2 lockTest = new LockTest2();

            lockTest.SpinLockTest();
        });


        CompletableFuture.runAsync(() -> {
            LockTest2 lockTest2 = new LockTest2();

            lockTest2.SpinLockTest();
        });


        CompletableFuture.runAsync(() -> {
            LockTest2 lockTest3 = new LockTest2();

            lockTest3.SpinLockTest();
        });


        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void main2() {

        CompletableFuture.runAsync(() -> {
            new LockTest3().SpinLockTest();
        });

        CompletableFuture.runAsync(() -> {
            new LockTest3().SpinLockTest();
        });

        CompletableFuture.runAsync(() -> {
            new LockTest3().SpinLockTest();
        });

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void main3() {
        Jedis jedisClient = RedisClient.getClient();

        for (int i = 0; i < 200; i++) {
//            CompletableFuture.runAsync(() -> {
            jedisClient.set("zhao", UUID.randomUUID().toString());
            System.out.println("set 成功");
            String zhao = jedisClient.get("zhao");
            if (zhao != null) {
                jedisClient.del("zhao");
                System.out.println("del 成功");
            }
//            });
        }
        try {
            Thread.sleep(10 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void main4() {

        for (int i = 0; i < 5; i++) {
            int num = i;
            CompletableFuture.runAsync(() -> {
                new LockTest4().SpinLockTest("aaa" + num);
            });
        }


        try {
            Thread.sleep(20 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ConcurrentMap<String, Integer> dataMap = LockTest4.dataMap;
        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            System.out.println(entry.getKey() + " 次数： " + entry.getValue());
        }


    }

    @Test
    public void main5() {

        ExecutorService executorService = Executors.newFixedThreadPool(4);


        for (int i = 0; i < 100; i++) {
            int num = i;
            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(Long.parseLong(String.valueOf(random.nextInt(1000))));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new LockTest4().demo("aaa-" + num);
            }, executorService);
        }


        try {
            Thread.sleep(20 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ConcurrentMap<String, Integer> dataMap = LockTest4.dataMap;
        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            System.out.println(entry.getKey() + " 次数： " + entry.getValue());
        }


    }
}
