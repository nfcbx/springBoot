package com.zsx.sso.dao;

import com.zsx.sso.entity.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<TUser, Integer> {


    List<TUser> findByUsername(String username);



    /**
     *  //方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
     *     List<Users> findByName(String name);
     *
     *     List<Users> findByNameAndAge(String name,Integer age);
     *
     *     List<Users> findByNameLike(String name);
     */
}
