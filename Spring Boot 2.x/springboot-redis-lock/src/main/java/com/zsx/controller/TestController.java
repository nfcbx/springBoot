package com.zsx.controller;

import com.alibaba.fastjson.JSON;
import com.zsx.config.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhaoshuxue3
 * @Date 2019/4/9 20:11
 **/
@RestController
public class TestController {

    @Autowired
    RedisLock redisLock;


    //超时时间 10s
    private static final int TIMEOUT = 10 * 1000;
    // 商品key名称
    private static String key = "theKey";
    // 总库存量
    private static int total = 100;

    @GetMapping("/test")
    public void test() throws Exception {
//      模拟抢单用户，10w个
        List<String> userList = new ArrayList<>(100000);
//        IntStream.range(0, 100000).parallel().forEach(i -> {
//            userList.add("user-" + i);
//        });
        IntStream.range(0, 100000).forEach(i -> {
            userList.add("user-" + i);
        });
        System.out.println(userList.size());
//      抢到商品的用户
        List<String> luckyUsers = new ArrayList<>(100);

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (int i = 0; i < userList.size(); i++) {
            String userId = userList.get(i);
            System.out.println("----------------------------------    "+userId);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("用户: " + userId + " 开抢了");
                        String str = get(userId);
                        if (!StringUtils.isEmpty(str)) {
                            luckyUsers.add(str);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.println("线程还没执行结束呢");
            TimeUnit.SECONDS.sleep(1L);
        }
        System.out.println("线程执行结束...");

        luckyUsers.forEach(obj -> {
            System.out.println(obj);
        });

    }


    public String get(String userId) throws Exception {

        //用户开抢时间
        long startTime = System.currentTimeMillis();
        //未抢到的情况下，超时时间内继续获取锁
        while ((startTime + TIMEOUT) > System.currentTimeMillis()) {
            // 商品是否剩余
            if (total <= 0) {
                System.out.println("用户：" + userId + " 来晚了，已经抢光了");
                break;
            }

            if (redisLock.lock(key, userId)) {
                System.out.println("用户：" + userId + " 获得了锁");
                try {
                    // 商品是否剩余
                    if (total <= 0) {
                        System.out.println("用户：" + userId + " 来晚了，已经抢光了");
                        break;
                    }
                    // 模拟生成订单耗时操作
                    TimeUnit.MILLISECONDS.sleep(100L);
//                    TimeUnit.SECONDS.sleep(1L);
//                    TimeUnit.MILLISECONDS // 毫秒 1/1000秒
//                    TimeUnit.MICROSECONDS // 微秒 一百万分之一秒
//                    TimeUnit.NANOSECONDS  // 纳秒 十亿分之一秒


                    //抢购成功，库存减1，记录用户
                    total -= 1;

                    //抢单成功跳出
                    System.out.println("用户 " + userId + " 抢单成功跳出...所剩库存：" + total);

                    return userId + "抢单成功，所剩库存：" + total;
                } finally {
                    System.out.println("用户 " + userId + " 释放锁...");
                    //释放锁
                    redisLock.unlock(key, userId);
                }

            } else {
                //用户如果没拿到锁，在超时范围内继续请求锁，不需要处理
            }
        }
        return "";

/*
        // 加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(key, String.valueOf(time))) {
            throw new Exception("抱歉，人太多了，请稍后再试");
        }

        if (total == 0) {
            throw new Exception("抢光了");
        } else {
            // 减库存
            total = total - 1;

            Thread.sleep(100L);// 模拟减库存的处理时间

        }

        // 解锁
        redisLock.unlock(key, String.valueOf(time));
*/
    }
}
