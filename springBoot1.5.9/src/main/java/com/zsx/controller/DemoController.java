package com.zsx.controller;

import com.zsx.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by highness on 2018/1/22 0022.
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;


    @GetMapping("/a")
    public Object get(){
        return  "1";
    }

}
