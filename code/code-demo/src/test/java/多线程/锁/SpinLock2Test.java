package 多线程.锁;

import org.joda.time.DateTime;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class SpinLock2Test {


    private static Integer count = 0;


    public static void main(String[] args) {
        SpinLock2 spinLock = new SpinLock2();
        System.out.println("开始");
        for (int i = 0; i < 10; i++) {
            final int num = i;
            CompletableFuture.runAsync(() -> {
                spinLock.lock();

                printTime(num);

                if (count == 0) {
                    add();
                }
                spinLock.unlock();
            });
        }

        System.out.println("结束");

        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void add() {
        sleep();
        count += 1;
    }


    public static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printTime(Object object) {
//        System.out.println(DateTime.now().toString() + " : " + object);
        System.out.println(DateTime.now().toString("HH:mm:ss:sss") + " : " + object);
    }

}
