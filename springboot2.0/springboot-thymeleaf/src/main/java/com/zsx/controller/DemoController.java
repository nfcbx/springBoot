package com.zsx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/26 18:34
 **/
@Controller
public class DemoController {

    @RequestMapping("index")
    public String index(Map<String, Object> map) {
        map.put("name", "zhao");
        map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        map.put("url", "http://www.baidu.com");

        ArrayList<Map<Object, Object>> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<Object, Object> user = new HashMap<>();
            user.put("name", "name" + i);
            user.put("age", new Random().nextInt(10));
            user.put("flag", (i%2==0));

            users.add(user);
        }

        map.put("users", users);


        return "index";
    }

}
