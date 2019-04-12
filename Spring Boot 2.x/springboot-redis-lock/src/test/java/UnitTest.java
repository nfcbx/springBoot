import com.google.common.collect.Lists;
import com.zsx.App;
import com.zsx.config.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


/**
 * 模拟秒杀
 *
 * @author zhaoshuxue3
 * @Date 2019/4/9 18:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UnitTest {

    private static Logger logger = LoggerFactory.getLogger(UnitTest.class);

    @Autowired
    RedisLock redisLock;


    //超时时间 10s
    private static final int TIMEOUT = 10 * 1000;
    // 商品key
    private static String goodsKey = "goodsKey";
    // 商品库存量
    private static int goodsTotal = 100;
    // 模拟用户量  1w乘以
    private static int userTotal = 10000 * 10;

    private static Long startTime = System.currentTimeMillis();
    private static Long endTime = System.currentTimeMillis();


    @Test
    public void test() throws Exception {

//        创建模拟抢单用户
        ArrayList<String> userList = Lists.newArrayListWithCapacity(userTotal);

        IntStream.range(1, userTotal).forEach(i -> {
            userList.add("用户-" + i);
        });

        logger.info("抢单用户总人数为：{}", userList.size());

//        秒到商品的用户
        ArrayList<String> luckyUserList = Lists.newArrayListWithCapacity(goodsTotal);


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        startTime = System.currentTimeMillis();

        for (int i = 0, len = userList.size(); i < len; i++) {
            String userId = userList.get(i);
            logger.info("for get userId --->  {}", userId);
            executorService.execute(() -> {
                try {
                    logger.info("用户: {} 开抢了", userId);
                    String str = flashSale(userId);
                    if (!StringUtils.isEmpty(str)) {
                        luckyUserList.add(str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            logger.warn("线程还没执行结束呢");
            TimeUnit.SECONDS.sleep(1L);
        }
        logger.info("线程执行结束...");

        luckyUserList.forEach(obj -> {
            System.out.println(obj);
        });

        System.out.println("秒杀活动总耗时：" + ((endTime - startTime) / 1000) + " 秒");
    }


    /**
     * 秒杀
     *
     * @throws Exception
     */
    public String flashSale(String userId) {
//用户开抢时间
        long startTime = System.currentTimeMillis();
        //未抢到的情况下，超时时间内继续获取锁
        while ((startTime + TIMEOUT) > System.currentTimeMillis()) {
            // 商品是否剩余
            if (goodsTotal <= 0) {
                logger.warn("用户：{} 来晚了，已经抢光了", userId);
                break;
            }

            if (redisLock.lock(goodsKey, userId)) {
                logger.info("用户：{} 获得了锁", userId);
                try {
                    // 商品是否剩余
                    if (goodsTotal <= 0) {
                        logger.warn("用户：{} 来晚了，已经抢光了", userId);
                        break;
                    }
                    // 模拟生成订单耗时操作
                    try {
                        TimeUnit.MILLISECONDS.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //抢购成功，库存减1，记录用户
                    goodsTotal -= 1;

                    //抢单成功跳出
                    logger.info("用户 {} 抢单成功跳出...所剩库存：{}", userId, goodsTotal);

                    if (goodsTotal == 0) {
                        endTime = System.currentTimeMillis();
                    }
                    return userId + "抢单成功，所剩库存：" + goodsTotal;
                } finally {
                    logger.info("用户 {} 释放锁...", userId);
                    //释放锁
                    redisLock.unlock(goodsKey, userId);
                }
            } else {
                //用户如果没拿到锁，在超时范围内继续请求锁，不需要处理
            }
        }
        return null;
    }


    /**
     * 这个方法有问题
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {

        userTotal = 10;
        goodsTotal = 1;

//        创建模拟抢单用户
        ArrayList<String> userList = Lists.newArrayListWithCapacity(userTotal);

        IntStream.range(1, userTotal).forEach(i -> {
            userList.add("用户-" + i);
        });

        logger.info("抢单用户总人数为：{}", userList.size());

//        秒到商品的用户
        ArrayList<String> luckyUserList = Lists.newArrayListWithCapacity(goodsTotal);

        startTime = System.currentTimeMillis();

        userList.parallelStream().forEach(userId -> {
            logger.info("for get userId --->  {}", userId);
            logger.info("用户: {} 开抢了", userId);
            String str = flashSale(userId);
            if (!StringUtils.isEmpty(str)) {
                luckyUserList.add(str);
            }
        });

        TimeUnit.SECONDS.sleep(20L);

//        while (!executorService.isTerminated()) {
//            logger.warn("线程还没执行结束呢");
//            TimeUnit.SECONDS.sleep(1L);
//        }
        logger.info("线程执行结束...");

        luckyUserList.forEach(obj -> {
            System.out.println(obj);
        });

        System.out.println("秒杀活动总耗时：" + ((endTime - startTime) / 1000) + " 秒");


    }

}
