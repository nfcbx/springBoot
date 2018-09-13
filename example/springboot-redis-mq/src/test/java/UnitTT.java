import com.alibaba.fastjson.JSON;
import com.zsx.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;

/**
 * Created by ZSX on 2018/8/8.
 *
 * @author ZSX
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class UnitTT {


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1() {


        for (int i = 1; i < 10000; i++) {
            redisTemplate.opsForList().leftPush("list8", "" + i);
        }
//        ExecutorService executorService = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        System.out.println(start);
        for (int i = 1; i < 8000; i++) {
            final int n = i;
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
                    Object list1 = redisTemplate.opsForList().rightPop("list8");
                    System.out.println(n + " : " + list1);
//                    Long size = redisTemplate.opsForList().size("list2");
//                    System.out.println(size);
                    System.out.println(System.currentTimeMillis());
//                }
//            });
        }

        long end = System.currentTimeMillis();
        System.out.println("8899： " + (end - start));

        try {
            Thread.sleep(1000 * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void t2(){
        Set list = redisTemplate.keys("list*");
        System.out.println(JSON.toJSONString(list));

//        Long expire = redisTemplate.getExpire("list3");
//        System.out.println(expire);
//        Boolean expire1 = redisTemplate.expire("list3", 10L, TimeUnit.SECONDS);
//        System.out.println(expire1);
//        expire = redisTemplate.getExpire("list3");
//        System.out.println(expire);
    }

}



