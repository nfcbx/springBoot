package com.zsx;

import com.alibaba.fastjson.JSON;
import com.zsx.dao.UserDao;
import com.zsx.entity.User;
import com.zsx.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class AppTests {

    Logger logger = LoggerFactory.getLogger(AppTests.class);

    @Autowired
    UserDao userDao;
    @Autowired
    private UserService userService;

    @Test
    void test1() {
        List<User> all = userService.getAll();
        System.out.println(8899);
        System.out.println(JSON.toJSONString(all));

        System.out.println();

        List<User> all2 = userService.getAll();
        System.out.println(8899);
        System.out.println(JSON.toJSONString(all2));
    }

    @Test
    void 过期测试5秒过期() throws InterruptedException {
        // 设置 flushInterval="5000" ，表示5秒后过期
        List<User> list1 = userService.getAll();
        logger.info("第一次查询数据：{}", JSON.toJSONString(list1));

        logger.info("开始休眠2秒");
        TimeUnit.SECONDS.sleep(2L);

        List<User> list2 = userService.getAll();
        logger.info("第二次查询数据：{}", JSON.toJSONString(list2));

        logger.info("开始休眠5秒，使缓存过期");
        TimeUnit.SECONDS.sleep(5L);

        List<User> list3 = userService.getAll();
        logger.info("第三次查询数据：{}", JSON.toJSONString(list3));

    }

    @Test
    void 测试更新数据后缓存被清空() throws InterruptedException {
        // 只要是 cud 操作就会清空缓存
        List<User> list1 = userService.getAll();
        logger.info("第一次查询数据：{}", JSON.toJSONString(list1));

        // 更新测试
//        User user = new User(1L, "a", "a");
//        userDao.updateByPrimaryKey(user);

        // 新增测试
        User user = new User(null, "a", "a");
        userDao.insert(user);

        // 删除测试
//        userDao.deleteByPrimaryKey(2L);

        List<User> list2 = userService.getAll();
        logger.info("第二次查询数据：{}", JSON.toJSONString(list2));

        List<User> list3 = userService.getAll();
        logger.info("第三次查询数据：{}", JSON.toJSONString(list3));

    }

    @Test
    @Transactional
        // 开启事务，一级缓存才生效
    void test2() {
        List<User> users = userDao.selectAll();
        System.out.println(8899);
        System.out.println(JSON.toJSONString(users));

        System.out.println();

        List<User> users2 = userDao.selectAll();
        System.out.println(8899);
        System.out.println(JSON.toJSONString(users2));
    }

    @Test
    void contextLoads() {

    }

    @BeforeAll
    static void before() {
        System.out.println("测试开始");
    }

    @AfterAll
    static void after() {
        System.out.println("测试结束");
    }


}
