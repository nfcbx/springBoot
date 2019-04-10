package com.zsx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhaoshuxue3
 * @Date 2019/4/9 18:21
 **/
@RestController
public class DemoController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/")
    public String test(){
        return UUID.randomUUID().toString();
    }


    @GetMapping("/get")
    public String get(){
        return stringRedisTemplate.opsForValue().get("zsx");
    }

}
