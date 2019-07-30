package com.zsx.springbootderby.controller;

import com.zsx.springbootderby.config.RandomProperties;
import com.zsx.springbootderby.entity.User;
import com.zsx.springbootderby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/30 15:49
 **/
@RestController
public class DemoController {

    @Autowired
    private UserService userService;

    @Autowired
    private RandomProperties randomProperties;

    @GetMapping("add")
    public void insert() throws Exception {
        User user = new User();
        user.setName("zhao");
        user.setPwd("123456");
        userService.insert(user);
    }

    @GetMapping("get")
    public List<User> getAll() throws Exception {
        List<User> users = userService.selectAll();
        return users;
    }


    @GetMapping("123")
    public Map<String, Object> random() throws Exception {

        String secret = randomProperties.getSecret();
        int intNumber = randomProperties.getIntNumber();
        long longNumber = randomProperties.getLongNumber();
        String uuid = randomProperties.getUuid();
        int lessTen = randomProperties.getLessTen();
        int range = randomProperties.getRange();

        HashMap<String, Object> map = new HashMap<>();
        map.put("secret", secret);
        map.put("intNumber", intNumber);
        map.put("longNumber", longNumber);
        map.put("uuid", uuid);
        map.put("lessTen", lessTen);
        map.put("range", range);

        return map;
    }
}
