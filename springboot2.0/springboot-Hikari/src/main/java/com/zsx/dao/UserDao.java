package com.zsx.dao;

import com.zsx.entity.User;

import java.util.List;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
//@Mapper
public interface UserDao {

    List<User> selectAll();

}
