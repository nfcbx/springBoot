package 多线程.自旋锁;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

    private AtomicReference<Thread> cas = new AtomicReference<Thread>();

    private static int count = 0;

    public void lock() {
        Thread thread = Thread.currentThread();

        while (!cas.compareAndSet(null, thread)) {
//            System.out.println("自旋中。。。");
//            System.out.println("自旋次数：" + ++count);
        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        cas.compareAndSet(thread, null);
    }


}
