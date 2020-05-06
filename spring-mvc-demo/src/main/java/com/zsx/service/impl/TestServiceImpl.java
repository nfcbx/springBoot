package com.zsx.service.impl;

import com.alibaba.fastjson.JSON;
import com.zsx.config.datasource.DataSourceEnum;
import com.zsx.config.datasource.TargetDataSource;
import com.zsx.dao.UserDao;
import com.zsx.entity.User;
import com.zsx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserDao userDao;

    public String test() {
        return UUID.randomUUID().toString();
    }

    public void getDataFromMaster() {
        List<User> users = userDao.selectAll();
        System.out.println(JSON.toJSONString(users));
    }
    
    @TargetDataSource(DataSourceEnum.SLAVE)
    public void getDataFromSlave() {
        List<User> users = userDao.selectAll();
        System.out.println(JSON.toJSONString(users));
    }
}
