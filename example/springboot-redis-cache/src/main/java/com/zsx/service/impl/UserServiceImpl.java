package com.zsx.service.impl;


import com.zsx.dao.UserDao;
import com.zsx.entity.User;
import com.zsx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@Service
//@CacheConfig(cacheNames = "user") // 如果不在方法上加cacheNames，则必须要有这个
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
//    @Cacheable(value = "users") //缓存,这里没有指定key.
//    @Cacheable(value = "usersValue", key = "users")
    @Cacheable(cacheNames = "user", key = "'getAll'") // 此方法定义了cacheNames，则会覆盖类上@CacheConfig的cacheNames值
    public List<User> getAll() {
        return userDao.selectAll();
    }

}
