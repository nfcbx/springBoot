package com.zsx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by ZSX on 2018/12/4.
 *
 * @author ZSX
 */
@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/")
    public String test() {

        logger.info("打印日志：{}", new Date());

        return "Hello World!!!";
    }

}
