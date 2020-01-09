package com.zsx.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    // 核心线程数
    private static int corePoolSize = 4;
    // 最大线程数
    private static int maximumPoolSize = 8;
    // 存活时长
    private static long keepAliveTime = 5;
    // 存活时长单位
    private static TimeUnit seconds = TimeUnit.SECONDS;
    // 队列
    private static BlockingQueue queue = new LinkedBlockingQueue<>();


    private static ThreadPoolExecutor threadPoolExecutor = null;

    private ThreadPoolUtil() {
    }

    public static ThreadPoolExecutor getInstance() {
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, seconds, queue);
        }
        return threadPoolExecutor;
    }


}
