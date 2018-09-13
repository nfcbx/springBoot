import com.zsx.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ZSX on 2018/8/8.
 *
 * @author ZSX
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class UnitTTT {


    @Autowired
    RedisTemplate redisTemplate;

    private static String autoKey = "autoKey1";
    private static String codeKey = "list5";

    @Test
    public void test1() throws InterruptedException {


        for (int i = 1; i <= 100; i++) {
            long code = getCode();
            redisTemplate.opsForList().leftPush(codeKey, "" + code);
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    Long size = redisTemplate.opsForList().size(codeKey);
                    if (size.longValue() < 50L) {
                        for (int i = 1; i <= 100; i++) {
                            long code = getCode();
                            redisTemplate.opsForList().leftPush(codeKey, "" + code);
                        }
                    }

                    try {
                        Thread.sleep(1000 * 10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

        for (int i = 1; i < 1000; i++) {
            final int n = i;
            Object list1 = redisTemplate.opsForList().rightPop(codeKey);
            System.out.println(n + " : " + list1);
            Thread.sleep(150L);

        }


    }


    private long getCode() {
        RedisAtomicLong billCode = new RedisAtomicLong(autoKey, redisTemplate.getConnectionFactory());
        long code = billCode.incrementAndGet();
        if (code < 1000001L) {
            billCode.set(1000001L);
            code = billCode.incrementAndGet();
        }
        return code;
    }

}
