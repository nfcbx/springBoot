package 多线程.自旋锁;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import 单元测试.redisDemo.RedisClient;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class LockTest2 {

    public Jedis client;

    @Before
    public void 开始() {
        client = RedisClient.getClient();
    }

    @After
    public void 结束() {
        client.close();
    }


    private static String key = "test-key";

    @Test
    public void SpinLockTest() {

        SpinLock spinLock = new SpinLock();
        System.out.println("开始");
        for (int i = 0; i < 10; i++) {
            final int num = i;
            CompletableFuture.runAsync(() -> {
                spinLock.lock();


                String data = getData();
                printTime(num + " : " + data);
                if (StringUtils.isBlank(data)) {
                    createData();
                }

                spinLock.unlock();
            });
        }

        System.out.println("结束");

        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public String getData() {
        String data = client.get(key);
        System.out.println("从缓存中获取数据：" + data);
        return data;
    }


    public void createData() {
        try {
            // 模拟网络耗时2s
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
        }
        client.set(key, "testData");
        client.expire(key, 5);
    }


    public static void add() {
//        sleep();
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printTime(Object object) {
//        System.out.println(DateTime.now().toString() + " : " + object);
        System.out.println("打印 " + DateTime.now().toString("HH:mm:ss:sss") + " : " + object);
    }
}
