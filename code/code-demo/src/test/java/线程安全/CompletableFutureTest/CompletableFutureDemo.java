package 线程安全.CompletableFutureTest;

import org.joda.time.LocalDateTime;
import org.junit.Test;

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

    public void test2() throws Exception {
//        runAsync 不需要返回结果
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

        });



    }
    @Test
    public void test3() throws Exception {
//        supplyAsync 获取返回结果
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            return "done";
        });
        System.out.println("1");
        String str = future.get();
        System.out.println("2");
        System.out.println(str);
    }
    public void test4() throws Exception {}
    public void test5() throws Exception {}
    public void test6() throws Exception {}
    public void test7() throws Exception {}
    public void test8() throws Exception {}
    public void test9() throws Exception {}
    public void test10() throws Exception {}
}
