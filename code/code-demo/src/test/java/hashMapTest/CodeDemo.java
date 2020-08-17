package hashMapTest;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZSX on 2018/10/30.
 *
 * @author ZSX
 */
public class CodeDemo {

    //    @Test(invocationCount = 3, threadPoolSize = 2)  不好用，需要xml

    @RepeatedTest(5)
    public void test1() throws Exception {

//        HashMap<String, String> map = new HashMap<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 100; i++) {
            final String str = "" + i;
            CompletableFuture.runAsync(() -> {
                map.put(str, str);
            });
        }

        Thread.sleep(4 * 1000L);
        System.out.println(map.size());

    }

    public static void main(String[] args) {
        Map map = new HashMap();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("123", "456");
        System.out.println(concurrentHashMap);

        NewConcurrentHashMap newConcurrentHashMap = new NewConcurrentHashMap(2);

        newConcurrentHashMap.put("123", "456");

        System.out.println(newConcurrentHashMap);


        NewHashMap newHashMap = new NewHashMap<>();
        Object put = newHashMap.put("asdf", "asdfasdfasd1");
        System.out.println(put);
        put = newHashMap.put("asdf", "asdfasdfasd2");
        System.out.println(put);

        System.out.println(newHashMap);

        System.out.println(1 << 4);
        System.out.println(2 << 4);
        System.out.println(3 << 4);

        System.out.println("HashMap最大容量：" + (1 << 30));

    }

}
