package hashMapTest.HashMapUnsafeTest;

import java.util.HashMap;

/**
 * Created by ZSX on 2018/11/7.
 *
 * @author ZSX
 */
public class HashMapUnSafeTest {

    //  使用ConcurrentHashMap可运行正常
//    final static ConcurrentHashMap<String, String> ch = new ConcurrentHashMap<>();
    final static HashMap<String, String> ch = new HashMap<>();

    //  运行的线程
    public static class R implements Runnable {

        private String end;

        public R(String end) {
            super();
            this.end = end;
        }

        @Override
        public void run() {
            while (!this.end.equals(ch.get("end"))) {
                System.out.println(this.end);
            }
            System.out.println(this.end + ": is End!");
        }

    }

    public static void main2(String[] args) {
        //  启动线程
        for (int i = 0; i < 10; i++) {
            new Thread(new R(String.valueOf(i))).start();
        }

//        new Thread(() -> {
//            int j = 0;
//            while (j < 10) {
//                ch.put("end", String.valueOf(j));
//                //  让其他线程有执行的机会
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                j++;
//            }
//        }).start();
    }


    public static void main(String[] args) {
        MapThread t0 = new MapThread();
        MapThread t1 = new MapThread();
        MapThread t2 = new MapThread();
        MapThread t3 = new MapThread();
        MapThread t4 = new MapThread();
        MapThread t5 = new MapThread();
        MapThread t6 = new MapThread();
        MapThread t7 = new MapThread();
        MapThread t8 = new MapThread();
        MapThread t9 = new MapThread();

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
    }

}

