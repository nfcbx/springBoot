package com.zsx.controller;

import com.alibaba.fastjson.JSON;
import com.zsx.config.RedisMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

/**
 * Created by ZSX on 2018/2/2.
 *
 * @author ZSX
 */
@RestController
public class DemoController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisMessageSender redisMessageSender;

    @GetMapping("demo")
    public String test1() {
        Set<String> keys = redisTemplate.keys("*");
        System.out.println();
        System.out.println(JSON.toJSONString(keys));
        System.out.println();
        return UUID.randomUUID().toString();
    }


    @GetMapping("send")
    public String send(String msg) {
        redisMessageSender.sendMessage("chat", msg);
        return "success";
    }
}
