package com.zsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@SpringBootApplication
//@MapperScan("com.zsx.dao") // 扫描mapper （也可以直接在mapper类添加注释@Mapper即可）
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
