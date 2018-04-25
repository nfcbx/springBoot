package com.zsx.dao;

import com.zsx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@Mapper
public interface UserMapper extends BaseDao<User>{

    List<User> selectAll();

}
