package com.zsx;

import com.zsx.domain.User;
import com.zsx.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by highness on 2018/1/7 0007.
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/users")
    @ResponseBody
    List<User> findAll() {
        List<User> all = userRepository.findAll();
        return all;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}