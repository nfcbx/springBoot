import com.alibaba.fastjson.JSON;
import com.zsx.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Set;
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
public class UnitT {


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1() {

        Set keys = redisTemplate.keys("");

        System.out.println(JSON.toJSONString(keys));

        RedisAtomicLong billCode = new RedisAtomicLong("oasis_web_bill_uk", redisTemplate.getConnectionFactory());
        long code = billCode.incrementAndGet();
        System.out.println(code);

//        redisTemplate.opsForValue().set("zhao", "2");

        for (int i = 0; i < 30; i++) {
            Long aLong = redisTemplate.opsForList().leftPush("list1", "aaa" + i);
        }
//        System.out.println(aLong);
//        aLong = redisTemplate.opsForList().leftPush("list1", "bbb");
//        System.out.println(aLong);
//        aLong = redisTemplate.opsForList().leftPush("list1", "ccc");
//        System.out.println(aLong);

//        Object list1 = redisTemplate.opsForList().rightPop("list1");
//        System.out.println(list1);
//        list1 = redisTemplate.opsForList().rightPop("list1");
//        System.out.println(list1);
//        list1 = redisTemplate.opsForList().rightPop("list1");
//        System.out.println(list1);


        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            final String key = "" + i + 1;
            final Object[] value = {null};
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//                    asdf(key);
                    getValue();
                }
            });
            if (value[0] == null) {
                try {
                    Thread.sleep(1000 * 1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            Thread.sleep(1000 * 5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void asdf(String flag) {
        redisTemplate.watch("zhao");

        // get  update
        Object zhao = redisTemplate.opsForValue().get("zhao");
        System.out.println(zhao);
        Integer integer = Integer.valueOf(zhao.toString());
        System.out.println(flag + " 得到：" + integer);
        integer += 1;

        redisTemplate.multi();

        // set
        redisTemplate.opsForValue().set("zhao", integer);
        System.out.println(flag + " 更新：" + integer);

        redisTemplate.exec();
    }

    private Object getValue() {
        Boolean lock = redisTemplate.hasKey("keyLock");
        if (lock) {
            return null;
        } else {
            Object list1 = redisTemplate.opsForList().rightPop("list1");
            System.out.println(list1);
            Long size = redisTemplate.opsForList().size("list1");
            if (size.longValue() < 10L) {
                addList();
            }
            return list1;
        }
    }

    private synchronized void addList() {
        Boolean lock = redisTemplate.hasKey("keyLock");
        if (lock) {
            System.out.println("不进行添加数据的操作");
        } else {
            System.out.println("准备添加数据");
            Boolean execute = (Boolean) redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(serializer.serialize("keyLock"), serializer.serialize("keyLock"));
                    connection.close();
                    return success;
                }
            });

            if (execute) {
                for (int i = 0; i < 50; i++) {
                    redisTemplate.opsForList().leftPush("list1", "aaa" + i);
                }
                redisTemplate.delete("keyLock");
            } else {
                System.out.println("正在被使用 。。。 新增数据");
            }

        }

    }
}
