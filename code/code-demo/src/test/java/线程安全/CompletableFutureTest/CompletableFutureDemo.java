package 线程安全.CompletableFutureTest;

import org.joda.time.LocalDateTime;

import java.util.concurrent.*;

public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        CompletableFutureDemo demo = new CompletableFutureDemo();
        demo.test1();
    }


    public void test() {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 8, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));

        for (int i = 0; i < 10; i++) {
            final int num = i;
            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getNow() + " : " + num);
            }, threadPoolExecutor);
        }

        System.out.println(getNow() + "  主线程执行结束了");

        try {
            TimeUnit.SECONDS.sleep(15L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getNow() {
        return LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }


    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

        future.complete("test");

        String s = future.get();
        System.out.println(s);
    }
}
