package com.zsx.springbootkisso;

import com.alibaba.fastjson.JSON;
import com.zsx.springbootkisso.entity.Tuser;
import com.zsx.springbootkisso.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UnitTest {

    @Autowired
    UserService userService;

    @Test
    void test1() {
        Tuser zhao = userService.getUser("zhao");

        System.out.println(JSON.toJSONString(zhao));
    }

}
