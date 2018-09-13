package com.zsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZSX on 2018/1/17.
 *
 * @author ZSX
 */
@RestController
@SpringBootApplication
public class App {

    @RequestMapping("/mock")
    public String mock(
            @RequestParam(value = "name") String name
    ) {
        return "参数:" + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
