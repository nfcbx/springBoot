package com.zsx;

import com.zsx.dao.UserDao;
import com.zsx.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;

import java.util.List;

@SpringBootTest
class SpringbootShardingsphereApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        System.out.println(userDao);
    }

    @Test
    void save() {
        User user = new User();
        user.setUsername("demo1");
        user.setPassword("demo1");

        userDao.insert(user);
        System.out.println("插入成功" + user.getId());
    }

    @Test
    void get() {
        List<User> users = userDao.selectAll();
        System.out.println("查询结果" + users.size());
        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }
}
