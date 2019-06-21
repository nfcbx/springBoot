package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhaoshuxue3
 * @Date 2019/6/21 18:36
 **/
@Component
@Order(value = 2)
public class BeforeRunner2 implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("implements ApplicationRunner");
    }
}
