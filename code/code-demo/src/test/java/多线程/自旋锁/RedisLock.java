package 多线程.自旋锁;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import 单元测试.redisDemo.RedisClient;

public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private Jedis jedis = RedisClient.getClient();

    public boolean lock(String key, String value) {
//        logger.info("  redis获取分布式锁 " + value);

        long one = 1L;

        Long setnx = jedis.setnx(key, value);
//        logger.info("{} 执行 setnx 命令 = {}", value, setnx);
        if (setnx.longValue() == one) {
            logger.info("  redis获取分布式锁 " + value + " 得到锁");
            jedis.expire(key, 10);
            return true;
        }

        String theValue = jedis.get(key);
        if (StringUtils.isNotBlank(theValue) && theValue.equals(value)) {
            logger.info("  redis获取分布式锁 " + value + " 得到锁");
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
        logger.info("  redis释放分布式锁 " + value);
        String theValue = jedis.get(key);
        if (StringUtils.isNotBlank(theValue) && theValue.equals(value)) {
            jedis.del(key);
        }
    }

}
