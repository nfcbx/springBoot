package 多线程.锁;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

    private AtomicReference<Thread> cas = new AtomicReference<Thread>();

    public void lock() {
        Thread thread = Thread.currentThread();

        while (!cas.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        cas.compareAndSet(thread, null);
    }


}
