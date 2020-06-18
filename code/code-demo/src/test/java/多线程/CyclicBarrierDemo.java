package 多线程;

import org.junit.Test;

import java.util.concurrent.*;

public class CyclicBarrierDemo {


    private static ExecutorService executorService = Executors.newCachedThreadPool();
//    private static ExecutorService executorService = Executors.newFixedThreadPool(4);


    @Test
    public void tt1() {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                () -> {
                    System.out.println("欢迎来到王者荣耀！全军出击！");
                });


        for (int i = 1; i <= 5; i++) {
            int num = i;

            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                    System.out.println("玩家" + num + "进入游戏");
                    cyclicBarrier.await(1L, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("继续执行");

        executorService.shutdown();

        try {
            executorService.awaitTermination(5L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
        }
//

    }

}
