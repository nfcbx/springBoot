package com.zsx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by ZSX on 2018/2/2.
 *
 * @author ZSX
 */
@Service
public class RedisMessageSender {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void sendMessage(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }
}
