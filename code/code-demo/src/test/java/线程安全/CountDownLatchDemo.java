package 线程安全;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchDemo {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        CountDownLatch countDownLatch = new CountDownLatch(10);
        AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                int addAndGet = atomicInteger.addAndGet(1);

                String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                System.out.println(format);
                System.out.println(addAndGet);
                System.out.println();

                countDownLatch.countDown();
            });
        }

        executorService.shutdown();

        try {
            countDownLatch.await(); // 等待任务结束，即，countDownLatch的值 = 0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
