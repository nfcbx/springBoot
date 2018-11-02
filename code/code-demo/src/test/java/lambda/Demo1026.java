package lambda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZSX on 2018/10/26.
 *
 * @author ZSX
 */
public class Demo1026 {

    public static void main(String[] args) {

        for (int i = 9999979; i < 9999999; i++) {
            System.out.println(i&15);
        }

//        System.out.println(11&(16-1));
//        System.out.println(12&(16-1));
//        System.out.println(13&(16-1));



    }

    public static void main1(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(111, 222, 333, 444, 555);

        System.out.println("以前的循环方式");
        for (Integer integer : list) {
            System.out.println(integer);
        }

        System.out.println("lambda循环方式");
        list.forEach((object) -> System.out.println(object));

        System.out.println("在 Java 8 中使用双冒号操作符");
        list.forEach(System.out::println);


        ExecutorService executorService = Executors.newSingleThreadExecutor();


        executorService.execute(() -> list.size());

    }

}



