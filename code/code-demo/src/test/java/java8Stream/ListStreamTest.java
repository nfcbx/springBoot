package java8Stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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
        System.out.println();

//        TODO
        list.parallelStream().filter(item -> {
            return (int) item > 5;
        });
        System.out.println(list);

        list.parallelStream().sorted((obj1, obj2) -> {
            return (int) obj1 - (int) obj2;
        });
        System.out.println(list);

        boolean anyMatch = list.parallelStream().anyMatch(item -> {
            return (int) item > 55;
        });

        System.out.println(anyMatch);

    }

    @Test
    public void test2() {
        CopyOnWriteArrayList<Object> list = Lists.newCopyOnWriteArrayList();
        IntStream.rangeClosed(1, 10)
//                .parallel()
                .forEach(i -> {
                    list.add(i);
                });

        System.out.println(list);
    }

    @Test
    public void test3() {
        IntStream intStream1 = IntStream.rangeClosed(1, 5);
        IntStream intStream2 = IntStream.rangeClosed(10, 15);

        IntStream intStream = IntStream.concat(intStream1, intStream2);

        intStream.forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    public void test4() {
        LongStream longStream = LongStream.builder()
                .add(1L)
                .add(3L)
                .build();

        longStream.forEach(item -> System.out.println(item));



    }

}
