package com.zsx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@SpringBootApplication
@MapperScan("com.zsx.dao")
@EnableCaching // 开启缓存
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
