package 多线程.自旋锁;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import 单元测试.redisDemo.RedisClient;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LockTest3 {

    public Jedis client;

    public LockTest3() {
        if (client == null) {
            client = RedisClient.getClient();
        }
    }

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private Random random = new Random();

    //超时时间 10s
    private static final int TIMEOUT = 10 * 1000;

    private static String dataKey = "testDataKey";
    private static String dataValue = "testData";

    private static String key = "test-key";

    private RedisLock redisLock = new RedisLock();

    private SpinLock spinLock = new SpinLock();
//    private static SpinLock spinLock = new SpinLock();

    private static String dataCache = null;

    @Test
    public void SpinLockTest() {

        Thread thread = Thread.currentThread();
        String name = thread.getName();
        printTime("ThreadName  :   " + name);

        System.out.println("开始");
        for (int i = 0; i < 100; i++) {
            final int num = i;
//            CompletableFuture.runAsync(() -> {
//                demo(num);
//            });

            executorService.submit(() -> {
                demo(name + " >>> " + num);
            });

            try {
                TimeUnit.MILLISECONDS.sleep(Long.parseLong(String.valueOf(random.nextInt(300))));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println("结束");

//        try {
//            TimeUnit.SECONDS.sleep(5L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public String demo(Object object) {
        spinLock.lock();

        String data = getData();

        if (StringUtils.isBlank(data)) {
            printTime(object + " <> " + "重新获取数据");
//            data = createData();
            data = singleCreateData();
        }

        printTime(object + " : " + data);

        spinLock.unlock();

        return data;
    }


    public String getData() {
        String data = client.get(dataKey);
//        System.out.println("从缓存中获取数据：" + data);
        return data;
    }

    public String singleCreateData() {
        String name = Thread.currentThread().getName();
        // 开始时间
        long startTime = System.currentTimeMillis();
        // 超时时间内继续获取锁
        while ((startTime + TIMEOUT) > System.currentTimeMillis()) {

            String data1 = dataCache;
            if (data1 != null) {
                return data1;
            }

            if (redisLock.lock(key, name)) {
                try {
                    String data = createData(name);
                    dataCache = data;
                    return data;
                } finally {
                    redisLock.unlock(key, name);
                    dataCache = null;
                }
            }
        }
        return null;
    }

    public String createData(String name) {
        try {
            // 模拟网络耗时2s
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
        }
//        String value = dataValue;
        String value = name;

        client.set(dataKey, value);
        client.expire(dataKey, 1);

        return value;
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
        DateTime now = DateTime.now();
//        System.out.println("打印 " + now.toString("HH:mm:ss:sss") + " : " + object);
        int minuteOfHour = now.getMinuteOfHour();
        int secondOfMinute = now.getSecondOfMinute();
        int millisOfSecond = now.getMillisOfSecond();

        System.out.println(now.toString("HH:mm:ss:sss") + " 打印 " + minuteOfHour + ":" + secondOfMinute + ":" + millisOfSecond + "" + " : " + object);


    }

    @Test
    public void test1() {

        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(200);
            System.out.println(num);
        }
    }
}
