package 单元测试.redisDemo;

import redis.clients.jedis.Jedis;


public class RedisProducer {

    public static void main(String[] args) {
        Jedis client = RedisClient.getClient();
        int i = 1;
        while (i < 100) {
            System.out.println(i);
            String num = String.valueOf(i);
            Long list = client.lpush("list", num);
            System.out.println("生产= " + num + " size= " + list);
            RedisTest1.sleepThread();
            i++;
        }
    }
}
