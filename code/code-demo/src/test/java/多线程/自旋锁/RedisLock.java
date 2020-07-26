package 多线程.自旋锁;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import redis.clients.jedis.Jedis;
import 单元测试.redisDemo.RedisClient;

public class RedisLock {

    private Jedis jedis = RedisClient.getClient();

    public boolean lock(String key, String value) {
//        System.out.println(DateTime.now().toString("HH:mm:ss:sss") + "  redis获取分布式锁 " + value);

        long one = 1L;

        Long setnx = jedis.setnx(key, value);

        if (setnx.longValue() == one) {
            System.out.println(DateTime.now().toString("HH:mm:ss:sss") + "  redis获取分布式锁 " + value + " 得到锁");
            jedis.expire(key, 10);
            return true;
        }

        String theValue = jedis.get(key);
        if (StringUtils.isNotBlank(theValue) && theValue.equals(value)) {
            System.out.println(DateTime.now().toString("HH:mm:ss:sss") + "  redis获取分布式锁 " + value + " 得到锁");
            return true;
        }

        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        System.out.println(DateTime.now().toString("HH:mm:ss:sss") + "  redis释放分布式锁 " + value);
        String theValue = jedis.get(key);
        if (StringUtils.isNotBlank(theValue) && theValue.equals(value)) {
            jedis.del(key);
        }
    }
}
