package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @PostMapping("/getContent")
    public String getContent(
            @RequestParam(value = "content") String content
    ){
        System.out.println(content);
        return content;
    }

}
