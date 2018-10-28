import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by highness on 2018/10/28 0028.
 */
public class MapTest {

    public static void main(String[] args) {
        test1();
    }


    public static void test1() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);


        Map map = new HashMap();

        map.put("", "");

        for (int i = 0; i < 1000; i++) {
            int num = new Random().nextInt();
            int num2 = 1000 - i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    map.put(num, num);
                }
            });
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    map.put(num, num);
                }
            });
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    map.put(num, num);
                }
            });
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    map.put(num, num);
                }
            });
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    map.put(num, num);
                }
            });
        }


        executorService.shutdown();

        System.out.println(map.size());
//        System.out.println(map.toString());


    }

}
