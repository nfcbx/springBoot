package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhaoshuxue3
 * @Date 2019/6/21 18:36
 **/
@Component
@Order(value = 1)
public class BeforeRunner implements CommandLineRunner {

    @Autowired
    private LoggingSystem loggingSystem;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("implements CommandLineRunner");
//        动态修改日志级别
        System.out.println(loggingSystem);
        loggingSystem.setLogLevel(null, LogLevel.DEBUG);
    }
}


/**
 * 默认顺序
 * implements ApplicationRunner
 * implements CommandLineRunner
 * <p>
 * <p>
 * 可以加 @Order 注解指定顺序
 *
 * @Order 注解的执行优先级是按value值从小到大顺序
 */