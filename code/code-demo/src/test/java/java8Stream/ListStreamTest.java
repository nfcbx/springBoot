package java8Stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;

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
        list.add(1);
        list.add(3);
        list.add(2);

        list.forEach(obj -> {
            System.out.println(obj);
        });

        System.out.println();

        list.parallelStream().forEachOrdered(obj -> {
            System.out.println(obj);
        });

    }

}
