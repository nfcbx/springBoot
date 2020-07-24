package 多线程.自旋锁;

import java.util.concurrent.CompletableFuture;

public class MainTest {


    public static void main(String[] args) {

        CompletableFuture.runAsync(() -> {

            LockTest2 lockTest = new LockTest2();

            lockTest.SpinLockTest();
        });


        CompletableFuture.runAsync(() -> {
            LockTest2 lockTest2 = new LockTest2();

            lockTest2.SpinLockTest();
        });


        CompletableFuture.runAsync(() -> {
            LockTest2 lockTest3 = new LockTest2();

            lockTest3.SpinLockTest();
        });


        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
