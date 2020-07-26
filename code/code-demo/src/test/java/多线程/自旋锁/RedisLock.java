package 多线程.自旋锁;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import 单元测试.redisDemo.RedisClient;

public class RedisLock {

    private Jedis jedis = RedisClient.getClient();

    public boolean lock(String key, String value) {

        long one = 1L;

        Long setnx = jedis.setnx(key, value);

        if (setnx.longValue() == one) {
            jedis.expire(key, 10);
            return true;
        }

        String theValue = jedis.get(key);
        if (StringUtils.isNotBlank(theValue) && theValue.equals(value)) {
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
        String theValue = jedis.get(key);
        if (StringUtils.isNotBlank(theValue) && theValue.equals(value)) {
            jedis.del(key);
        }
    }
}
