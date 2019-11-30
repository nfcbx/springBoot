package 多线程;

import org.joda.time.DateTime;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
}
