package com.zsx.springbootsmartdoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DemoController {

    @GetMapping("demo")
    public String demo(){
        return UUID.randomUUID().toString();
    }
}
