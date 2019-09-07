import com.google.common.collect.Lists;
import com.zsx.redisson.SpringbootRedissonApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootRedissonApplication.class})
public class RedissonLockTest {

    private static Logger logger = LoggerFactory.getLogger(RedissonLockTest.class);

    //超时时间 10s
    private static final int TIMEOUT = 10 * 1000;
    // 商品key
    private static String goodsKey = "productKey";
    // 商品库存量
    private static int goodsTotal = 100;
    // 模拟用户量  1w乘以
    private static int userTotal = 10000 * 1;

    private static Long startTime = System.currentTimeMillis();
    private static Long endTime = System.currentTimeMillis();

    @Test
    public void test3() throws InterruptedException {

//        模拟秒杀用户
        List<String> userList = Lists.newArrayListWithCapacity(userTotal);

        IntStream.range(1, userTotal).forEach(i -> userList.add("user_" + i));

//       秒到的用户集合
        List<String> goodLuckUsers = Lists.newArrayListWithCapacity(goodsTotal);

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);

        startTime = System.currentTimeMillis();

        for (int i = 0; i < userList.size(); i++) {
            String userId = userList.get(i);

            executorService.execute(() -> {
                logger.info("用户: {} 开抢了", userId);
                String str = work(client, userId);
                if (!StringUtils.isEmpty(str)) {
                    goodLuckUsers.add(str);
                }
            });
        }

        executorService.shutdown();


        while (!executorService.isTerminated()) {
            logger.warn("线程还没执行结束呢");
            TimeUnit.SECONDS.sleep(1L);
        }
        logger.info("线程执行结束...");

        goodLuckUsers.forEach(obj -> {
            System.out.println(obj);
        });

        System.out.println("秒杀活动总耗时：" + ((endTime - startTime) / 1000) + " 秒");

    }

    private String work(RedissonClient client, String userId) {

        //用户开抢时间
        long startTime = System.currentTimeMillis();
        //未抢到的情况下，超时时间内继续获取锁
        while ((startTime + TIMEOUT) > System.currentTimeMillis()) {
            // 商品是否剩余
            if (goodsTotal <= 0) {
                logger.warn("用户：{} 来晚了，已经抢光了", userId);
                break;
            }

            RLock lock = client.getLock(goodsKey);

            try {
                // 尝试加锁，最多等待2秒，上锁以后8秒自动解锁
                boolean bool = lock.tryLock(2L, 8L, TimeUnit.SECONDS);
                if (bool) {
                    logger.info("用户：{} 获得了锁", userId);
                    // 商品是否剩余
                    if (goodsTotal <= 0) {
                        logger.warn("用户：{} 来晚了，已经抢光了", userId);
                        break;
                    }
                    // 模拟生成订单耗时操作
                    TimeUnit.MILLISECONDS.sleep(100L);

                    //抢购成功，库存减1，记录用户
                    goodsTotal -= 1;

                    //抢单成功跳出
                    logger.info("用户 {} 抢单成功跳出...所剩库存：{}", userId, goodsTotal);

                    if (goodsTotal == 0) {
                        endTime = System.currentTimeMillis();
                    }
                    return userId + "抢单成功，所剩库存：" + goodsTotal;
                } else {
                    //用户如果没拿到锁，在超时范围内继续请求锁，不需要处理
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                logger.info("用户 {} 释放锁...", userId);
                // 释放锁
                lock.unlock();
            }
        }
        return null;
    }


    @Test
    public void contextLoads() {
        System.out.println(8899);
    }

    @Before
    public void before() {
        System.out.println();
        System.out.println("before 8899");
        System.out.println();
    }

    @After
    public void after() {
        System.out.println();
        System.out.println("end 8899");
        System.out.println();
    }
}
