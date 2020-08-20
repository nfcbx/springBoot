package 多线程;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class 线程可见性测试 {

    public static boolean flag;
//    public static volatile boolean flag;


    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!flag) {
                    i++;
                }
            }
        });


        thread.start();

        TimeUnit.SECONDS.sleep(2L);

        flag = true;
    }

    public static void main(String[] args) throws Exception {

        System.out.println(flag);

        线程可见性测试 aaa = new 线程可见性测试();
        CompletableFuture.runAsync(() -> {
            aaa.test1();
        });
        CompletableFuture.runAsync(() -> {
            try {
                aaa.test2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.MINUTES.sleep(10L);
    }


    @Test
    public void test1() {
        int i = 0;
        while (!flag) {
            i++;
        }
        System.out.println("结束了" + i);
    }


    @Test
    public void test2() throws InterruptedException {
        System.out.println("test2");
        TimeUnit.SECONDS.sleep(5L);
        flag = true;
        System.out.println("flag = true;");
    }

}
