package com.zsx.springbootderby.service.impl;

import com.zsx.springbootderby.dao.UserDao;
import com.zsx.springbootderby.entity.User;
import com.zsx.springbootderby.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/30 15:30
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create() {
        try {
            userDao.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer count() {
        return userDao.count();
    }

    @Override
    public List<User> selectAll() {
        try {
            return userDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> selectPage(int limit, int offset) {
        try {
            return userDao.selectPage(limit, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(User user) {
        try {
            userDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(List<String> ids) {
        try {
            userDao.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
