package 单元测试.redisDemo;

import redis.clients.jedis.Jedis;

public class RedisCustomer {

    public static void main(String[] args) {
        Jedis client = RedisClient.getClient();
        int i = 0;
        while (i < 10) {
            RedisTest1.sleepThread();
            String list = client.rpop("list");
            Long size = client.llen("list");
            System.out.println("消费= " + list + " size= " + size);
            if (size == 0) {
                RedisTest1.sleepThread();
                i++;
            }
        }
    }
}
