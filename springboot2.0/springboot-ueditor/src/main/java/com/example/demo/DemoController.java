package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by highness on 2019/4/30 0030.
 */
@RestController
public class DemoController {

    @GetMapping("/test")
    public String get(){
        return "zssx";
    }

}
