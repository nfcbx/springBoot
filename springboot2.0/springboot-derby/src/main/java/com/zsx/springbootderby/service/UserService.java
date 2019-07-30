package com.zsx.springbootderby.service;

import com.zsx.springbootderby.entity.User;

import java.util.List;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/30 15:30
 **/
public interface UserService {

    void create();

    Integer count();

    List<User> selectAll();

    List<User> selectPage(int limit, int offset);

    void insert(User user);

    void delete(List<String> ids);

    void update(User user);
}
