package 多线程.自旋锁;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import 单元测试.redisDemo.RedisClient;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LockTest4 {

    private static Logger logger = LoggerFactory.getLogger(LockTest4.class);

    public Jedis client;

    public LockTest4() {
        if (client == null) {
            client = RedisClient.getClient();
        }
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static ConcurrentMap<String, Integer> dataMap = Maps.newConcurrentMap();

    //超时时间 10s
    private static final int TIMEOUT = 10 * 1000;

    private static String dataKey = "testDataKey";
    private static String dataValue = "testData";

    private static String key = "test-key";

    private RedisLock redisLock = new RedisLock();


    public void SpinLockTest(String name) {

        Thread thread = Thread.currentThread();
        String threadName = thread.getName();

        String zhao = client.get("zhao");
        logger.info(zhao);

        System.out.println("开始");
        for (int i = 0; i < 10; i++) {
            final int num = i;
//            CompletableFuture.runAsync(() -> {
//                demo(num);
//            });
            System.out.println(num);
//            executorService.submit(() -> {
////                demo(name + " >>> " + num);
//            });
            demo(name + "-user-" + num);
        }
        System.out.println("结束");
    }

    public String demo(String userId) {
        // 获取数据
        String data = getData(userId);

        if (StringUtils.isBlank(data)) {
            logger.info("{} 重新获取数据", userId);

            data = singleCreateData(userId);
        }
        logger.info("{} 得到数据 {}", userId, data);
        return data;
    }

    public String getData(String userId) {

        String data = client.get(dataKey);
        if (data == null) {
            Integer integer = dataMap.get(userId);
            if (integer == null) {
                dataMap.put(userId, 1);
            } else {
                dataMap.put(userId, ++integer);
            }
        }
//        logger.info("{} 从缓存中获取数据", userId);
        return data;
    }


    public String singleCreateData(String userId) {
//        String name = Thread.currentThread().getName();
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 超时时间内继续获取锁
        while ((startTime + TIMEOUT) > System.currentTimeMillis()) {

            String newData = getData(userId);
            if (newData != null) {
                return newData;
            }

            if (redisLock.lock(key, userId)) {
                try {
                    String data = createData(userId);
                    return data;
                } finally {
                    redisLock.unlock(key, userId);
                }
            }
        }
        return null;
    }

    public String createData(String userId) {
        try {
            // 模拟网络耗时2s
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
        }
        String value = userId; //  + "的数据";

        client.set(dataKey, value);
        // 设置过期时间
        client.expire(dataKey, 3);

        return value;
    }


}
