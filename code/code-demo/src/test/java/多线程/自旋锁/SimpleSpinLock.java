package 多线程.自旋锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SimpleSpinLock implements Lock {

    private AtomicReference<Thread> cas = new AtomicReference<>();
    private int count = 0;

    @Override
    public void lock() {
        Thread thread = Thread.currentThread();
        if (thread == cas.get()) {
            ++count;
            return;
        }

        while (cas.compareAndSet(null, thread)) {

        }

    }

    @Override
    public void unlock() {
        Thread thread = Thread.currentThread();

        if (thread == cas.get()) {
            if (count > 0) {
                --count;
            } else {
                cas.set(null);
            }
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }


}
