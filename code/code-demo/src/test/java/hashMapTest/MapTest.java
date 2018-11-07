package hashMapTest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by highness on 2018/10/28 0028.
 */
public class MapTest {

    public static Map map = new HashMap();
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static int num = 1;

    static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    public static void main3(String[] args) {


        final HashMap<String, String> map1 = new HashMap<String, String>(2, 0.75f);
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map1.put(UUID.randomUUID().toString(), "");
                }
            }).start();
            System.out.println(map1.size());
        }


//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                test1();
//            }
//        });


//        executorService.shutdown();


//        MapTest mapTest = new MapTest();
//        mapTest.test2();


    }


    public static void test1() {
        for (int i = 0; i < 1000000; i++) {
            map.put(num++, num++);
        }
        System.out.println(map.size());
    }


    private static AtomicInteger ai = new AtomicInteger(0);
    private static Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>(1);


    class HashMapThread extends Thread {

        public void run() {
            while (ai.get() < 100000) {
                hashMap.put(ai.get(), ai.get());
                ai.incrementAndGet();
            }
        }
    }

    public void test2() {
        HashMapThread hmt0 = new HashMapThread();
        HashMapThread hmt1 = new HashMapThread();
        HashMapThread hmt2 = new HashMapThread();
        HashMapThread hmt3 = new HashMapThread();
        HashMapThread hmt4 = new HashMapThread();
        hmt0.start();
        hmt1.start();
        hmt2.start();
        hmt3.start();
        hmt4.start();
    }



    private HashMap map2 = new HashMap();

    public MapTest() {
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }
                System.out.println("t1 over");
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t2 over");
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t3 over");
            }
        };

        Thread t4 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t4 over");
            }
        };

        Thread t5 = new Thread() {
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t5 over");
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    public static void main(String[] args) {
        new MapTest();
    }

}
