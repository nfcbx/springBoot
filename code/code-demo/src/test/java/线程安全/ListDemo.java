package 线程安全;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by highness on 2018/10/28 0028.
 */
public class ListDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List list = new ArrayList();

        Collections.synchronizedList(list);


        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(1);
                }
            });
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(1);
                }
            });
        }


        executorService.shutdown();

        System.out.println(list.size());



    }

}
