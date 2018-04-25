package com.zsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZSX on 2018/1/17.
 *
 * @author ZSX
 */
@RestController
@SpringBootConfiguration
@SpringBootApplication
public class App {

//    @RequestMapping("/")
//    public String sendEmail() throws JsonProcessingException {
//        boolean isSend = EmailUtils.sendEmail("这是一封测试邮件", new String[]{"634790417@qq.com"}, null, "<h3><a href='http://www.baidu.com'>百度一下，你就知道</a></h3>", null);
//        return "发送邮件:" + isSend;
//    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
