package com.zsx.sso;

import com.alibaba.fastjson.JSON;
import com.zsx.sso.dao.UserDao;
import com.zsx.sso.entity.TUser;
import org.hibernate.criterion.Example;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootSsoApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void test1() {
        List<TUser> zhao = userDao.findByUsername("zhao");
        System.out.println(JSON.toJSONString(zhao));
    }

    @Test
    void contextLoads() {
        List<TUser> users = userDao.findAll();
        for (TUser user : users) {
            System.out.println(user.getId());
        }
    }

}
