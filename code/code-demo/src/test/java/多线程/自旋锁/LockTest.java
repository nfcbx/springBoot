package 多线程.自旋锁;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class LockTest {


    private static Integer count = 0;

    @Test
    public void SpinLockTest() {
        SpinLock spinLock = new SpinLock();
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

    @Test
    public void SimpleSpinLockTest() {
        SimpleSpinLock spinLock = new SimpleSpinLock();
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

    @Test
    public void TicketLockTest() {
        TicketLock spinLock = new TicketLock();
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


    @Test
    public void CLHLockTest() {
        // todo 这个有问题
        CLHLock spinLock = new CLHLock();
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


    @Test
    public void MCSLockTest() {
        MCSLock spinLock = new MCSLock();
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
//        sleep();
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        System.out.println("打印 " + DateTime.now().toString("HH:mm:ss:sss") + " : " + object);
    }
}
