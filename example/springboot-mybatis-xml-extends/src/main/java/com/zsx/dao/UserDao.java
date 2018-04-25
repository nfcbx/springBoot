package com.zsx.dao;

import com.zsx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@Mapper
public interface UserDao extends UserMapper {

    List<User> selectByParams(@Param("search") Object search);


}
