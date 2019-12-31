package concurrentTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/**
 * Created by ZSX on 2018/11/26.
 *
 * @author ZSX
 */
public class LongAdderDemo {

    static Long num = new Long(0L);
    static ExecutorService executorService = Executors.newCachedThreadPool();
    static LongAdder longAdder = new LongAdder();


    public static void main(String[] args) {



        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 1L);

        longAccumulator.accumulate(2L);

        longAccumulator.accumulate(3L);

        System.out.println(longAccumulator.longValue());

//        longAdder.increment();
//        System.out.println(longAdder.longValue());
        LongAdderDemo demo = new LongAdderDemo();

        for (int i = 0; i < 10000; i++) {
            demo.test1();
            demo.test2();
        }

        try {
            Thread.sleep(3 * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        System.out.println(num);
        System.out.println(longAdder.longValue());

    }


    public void test1() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                addNum();
            }
        });
    }

    public synchronized void addNum() {
        num += 1L;
    }

    public static void test2() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                longAdder.increment();
            }
        });
    }

}

