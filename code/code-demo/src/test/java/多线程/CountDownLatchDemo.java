package 多线程;

import org.joda.time.DateTime;

import java.util.concurrent.*;

public class CountDownLatchDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue());

        int num = 100;

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < num; i++) {
            int n = i;
            threadPoolExecutor.execute(() -> {
                runJob(n);

                countDownLatch.countDown();
            });
        }
        print("打印位置  001");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("打印位置  002");
        threadPoolExecutor.shutdown();

        print("打印位置  003");

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
        String time = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        System.out.println(time + " : " + str);
    }


}
