import com.zsx.App;
import com.zsx.config.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author zhaoshuxue3
 * @Date 2019/4/9 18:30
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UnitTest {

    @Autowired
    RedisLock redisLock;


    //超时时间 10s
    private static final int TIMEOUT = 10 * 1000;
    private static String key = "theKey";
    //    库存量
    private static int total = 100;


    @Test
    public void test() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }


    public void get() throws Exception {

        // 加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(key, String.valueOf(time))) {
            throw new Exception("抱歉，人太多了，请稍后再试");
        }

        if (total == 0) {
            throw new Exception("抢光了");
        } else {
            // 减库存
            total = total - 1;

            Thread.sleep(100L);// 模拟减库存的处理时间

        }

        // 解锁
        redisLock.unlock(key, String.valueOf(time));
    }

}
