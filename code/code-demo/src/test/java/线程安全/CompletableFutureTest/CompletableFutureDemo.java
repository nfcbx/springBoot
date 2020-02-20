package 线程安全.CompletableFutureTest;

import com.google.common.collect.Lists;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        CompletableFutureDemo demo = new CompletableFutureDemo();
        demo.test1();
    }


    public void test() {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 8, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));

        for (int i = 0; i < 10; i++) {
            final int num = i;
            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getNow() + " : " + num);
            }, threadPoolExecutor);
        }

        System.out.println(getNow() + "  主线程执行结束了");

        try {
            TimeUnit.SECONDS.sleep(15L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getNow() {
        return LocalDateTime.now().toString("HH:mm:ss");
    }


    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

        future.complete("test");

        String s = future.get();
        System.out.println(s);
    }

    public void test2() throws Exception {
//        runAsync 不需要返回结果
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

        });


    }

    @Test
    public void test3() throws Exception {
//        supplyAsync 获取返回结果
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "done";
        });
        System.out.println(getNow() + "  :  1");
        String str = future.get();
        System.out.println(getNow() + "    2");
        System.out.println(str);
    }

    @Test
    public void test4() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "第一个返回结果，需要5秒";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "第二个返回结果，需要3秒";
        });
        System.out.println(getNow() + "  开始执行");

        String str1 = future1.get();

        System.out.println(getNow() + "  打印第一个结果 " + str1);

        String str2 = future2.get();

        System.out.println(getNow() + "  打印第一个结果 " + str2);

//        22:25:22  开始执行
//        22:25:27  打印第一个结果 第一个返回结果，需要5秒
//        22:25:27  打印第一个结果 第二个返回结果，需要3秒
    }

    @Test
    public void test5() throws Exception {
        ArrayList<CompletableFuture<String>> resultList = Lists.newArrayList();
        CompletableFuture<String> future = null;
        for (int i = 0; i < 10; i++) {
            int num = i;
            future = CompletableFuture.supplyAsync(() -> {
                System.out.println(getNow() + "开始执行job  " + num);
                try {
                    TimeUnit.SECONDS.sleep(2L);
                } catch (InterruptedException e) {
                }
                return "返回 job " + num;
            });
            resultList.add(future);
        }

        System.out.println(getNow() + "  准备获取结果 ");

//        if (future != null) {
//            String res = future.get();
//            System.out.println(getNow() + " 结果为：  " + res);
//        }
        System.out.println(getNow() + " 结果size：  " + resultList.size());
        for (CompletableFuture<String> completableFuture : resultList) {
            String res = completableFuture.get();
            System.out.println(getNow() + "  结果为： " + res);
        }

        System.out.println(getNow() + "  全部结束 ");


    }

    @Test
    public void test6() throws Exception {


        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);

        List<CompletableFuture<Integer>> completableFutures = list.stream().map(item -> {
            return CompletableFuture.supplyAsync(() -> {
                return item + 10;
            });
        }).collect(Collectors.toList());


        CompletableFuture<Void> futures = CompletableFuture
                .allOf(completableFutures
                        .toArray(
                                new CompletableFuture[completableFutures.size()]));

        CompletableFuture<List<Integer>> futureList = futures.thenApply(v -> {
            return completableFutures.stream().map(item -> {
                return item.join();
            }).collect(Collectors.toList());
        });

        List<Integer> result = futureList.join();

        result.forEach(item -> System.out.println(item));


    }

    @Test
    public void test7() throws Exception {
        System.out.println();
    }

    @Test
    public void test8() throws Exception {
    }

    @Test
    public void test9() throws Exception {
    }

    @Test
    public void test10() throws Exception {
    }
}
