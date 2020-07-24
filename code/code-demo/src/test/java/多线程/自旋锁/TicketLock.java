package 多线程.自旋锁;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TicketLock implements Lock {

    private AtomicInteger serviceNum = new AtomicInteger(0);
    private AtomicInteger ticketNum = new AtomicInteger(0);

    private final ThreadLocal<Integer> myNum = new ThreadLocal<>();


    @Override
    public void lock() {
        myNum.set(ticketNum.getAndIncrement());
        while (serviceNum.get() != myNum.get()) {
            System.out.println("自旋ing");
        }
    }

    @Override
    public void unlock() {
        serviceNum.compareAndSet(myNum.get(), myNum.get() + 1);
        myNum.remove();
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
