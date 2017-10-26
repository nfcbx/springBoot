package com.zsx.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorUtil {
	
	// 队列的大小
    static final int queueSize = 5;
    //    线程池核心数
    static final int corePoolSize = 5;
    //    线程池最大连接数
    static final int maximumPoolSize = 5;
    //    最大空闲等待时间  （设置为1秒）
    static final long keepAliveTime = 1;

    //  定义一个size固定为5的线程队列
    static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(queueSize);
    //  创建一个线程池
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue);

    
	

}
