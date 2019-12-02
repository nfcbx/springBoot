package 多线程;

import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CountDownLatchDemo2 {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        print("串行开始时间:" + getTime());
        for (int i = 0; i < 10; i++) {
            int n = i;
            runJob(n);
        }
        print("串行结束时间:" + getTime());


        CountDownLatch countDownLatch = new CountDownLatch(6);

        print("并行开始时间:" + getTime());

        for (int i = 0; i < 10; i++) {
            int n = i;
            executorService.execute(() -> {
                runJob(n);
                countDownLatch.countDown();
            });
        }
        executorService.shutdown();
        print("打印位置  001");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("并行结束时间:" + getTime());

//        print("打印位置  002");


//        print("打印位置  003");
    }


    private static void runJob(int i) {
        try {
            // 休眠，模拟job运行耗时
            TimeUnit.SECONDS.sleep(2L);
            print("运行任务i=" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void print(String str) {
        String time = getTime();
        System.out.println(time + " : " + str);
    }

    private static String getTime() {
        return DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }

    private static String getLongTime(long start, long end) {
        long l = end - start;
        long second = l / 1000;
        return "  耗时 " + second + "秒";
    }

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        // 任务总数
        int total = 10;

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(2);

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2, 8, 5L, TimeUnit.SECONDS, queue);

        ArrayList<CompletableFuture<String>> resultList = Lists.newArrayList();

        CountDownLatch countDownLatch = new CountDownLatch(total);

        print("开始执行任务");

        for (int i = 0; i < total; i++) {
            int num = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    print("开始执行任务 ： " + num);
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                return "任务结果" + num;
            }, threadPoolExecutor);

            resultList.add(future);
        }

        print("for循环结束");

        try {
            print("开始等待计数器为0");
            countDownLatch.await(10L, TimeUnit.SECONDS);
            print("计数器已经为0");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        print("执行完成，开始打印结果");
        for (CompletableFuture<String> completableFuture : resultList) {
            try {
//                String res = completableFuture.get();
                String res = completableFuture.get(5L, TimeUnit.SECONDS);
                print("结果为： " + res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        print("结果打印完毕");

        print("任务全部执行结束");
        long end = System.currentTimeMillis();
        print(getLongTime(start, end));
    }
}
