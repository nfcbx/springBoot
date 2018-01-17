package com.zsx.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZSX on 2018/1/16.
 *
 * @author ZSX
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndAge(String name, Integer age);

    @Query(value = "from User u where u.name=:name")
    User findUser(@Param(value = "name") String name);


}
