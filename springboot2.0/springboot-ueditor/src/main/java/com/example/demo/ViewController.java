package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by highness on 2019/4/30 0030.
 */
@Controller
public class ViewController {

    @RequestMapping("/demo")
    public String toHouseListPage(HttpServletRequest request) {
        return "demo";
    }

    @RequestMapping("/")
    public String toindexPage(HttpServletRequest request) {
        return "index";
    }

}
