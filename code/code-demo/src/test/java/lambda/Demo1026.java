package lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

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





    }

    @Test
    public void test2(){

        System.out.println(Integer.MAX_VALUE); // 2147483647  二十一亿四千七百四十八万三千六百四十七
        System.out.println(Integer.MIN_VALUE); // -2147483648

        String str1 = "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc";
        String str2 = "efgefgefgefgefgefg";

        int hashCode1 = str1.hashCode();
        int hashCode2 = str2.hashCode();

        System.out.println(hashCode1); //
        System.out.println(hashCode2); //

        int num1 = hashCode1 >>> 16;
        int num2 = hashCode2 >>> 16;
        System.out.println(num1); //
        System.out.println(num2); //

        int hash1 = hashCode1 ^ num1;
        int hash2 = hashCode2 ^ num2;
        System.out.println(hash1);
        System.out.println(hash2);

        System.out.println(15 & hash1);
        System.out.println(15 & hash2);






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



