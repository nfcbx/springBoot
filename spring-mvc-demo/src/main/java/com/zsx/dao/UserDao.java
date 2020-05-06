package com.zsx.dao;

import com.zsx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserDao {

    int insert(User user);

    int updateByPrimaryKey(User user);

    int deleteByPrimaryKey(Long id);

    List<User> selectAll();

}
