package java8Stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * @author zhaoshuxue3
 * @Date 2019/4/12 18:00
 **/
public class ListStreamTest {

    public static void main(String[] args) throws Exception {
//        test1();

    }


    @Test
    public void test1() throws Exception {
        ArrayList<Object> list = Lists.newArrayList();
        IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            list.add(i);
        });

        list.forEach(obj -> {
            System.out.println(obj);
        });

        System.out.println();

        list.parallelStream().forEachOrdered(obj -> {
            System.out.println(obj);
        });

        System.out.println();

        list.stream().forEachOrdered(item -> {
            System.out.println(item);
        });

    }

    @Test
    public void test2(){
        CopyOnWriteArrayList<Object> list = Lists.newCopyOnWriteArrayList();
        IntStream.rangeClosed(1, 10)
//                .parallel()
                .forEach(i -> {
            list.add(i);
        });

        System.out.println(list);
    }

}
