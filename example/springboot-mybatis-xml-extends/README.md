# mybatis 通用mapper配置

 **实际应用中遇到的问题**

> 每次用工具生成mapper XML文件后，如果需要扩展自定义的方法，一般都是在生成的mapper文件里添加，但如果后续增减了表格字段，那么要不手动一个个修改mapper，要不就重新生成mapper，然后再把原先扩展的代码copy进去，总之这两种方法都很容易出现问题，且麻烦。

 解决方法：
每次用工具生成的mapperXML文件不动，利用 Mapper.xml继承机制 来扩展自定义的方法，这样后续重新生成mapper文件也不会影响扩展的代码。


## 工具生产的mapper文件

- TuserMapper.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zsx.dao.UserMapper">
    <resultMap id="BaseResultMap" type="com.zsx.entity.User">
        <id column="id" jdbcType="BIGINT" property="id"/>
        <result column="username" jdbcType="VARCHAR" property="username"/>
        <result column="password" jdbcType="VARCHAR" property="password"/>
        <result column="email" jdbcType="VARCHAR" property="email"/>
        <result column="mobile" jdbcType="VARCHAR" property="mobile"/>
        <result column="nickname" jdbcType="VARCHAR" property="nickname"/>
    </resultMap>
    <sql id="Base_Column_List">
    id, username, password, email, mobile, nickname
  </sql>
    <select id="selectByPrimaryKey" parameterType="java.lang.Long" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from t_user
        where id = #{id,jdbcType=BIGINT}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
    delete from t_user
    where id = #{id,jdbcType=BIGINT}
  </delete>
    <insert id="insert" parameterType="com.zsx.entity.User">
        <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into t_user (username, password, email,
        mobile, nickname)
        values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR},
        #{mobile,jdbcType=VARCHAR}, #{nickname,jdbcType=VARCHAR})
    </insert>
    <insert id="insertSelective" parameterType="com.zsx.entity.User">
        <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into t_user
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="username != null">
                username,
            </if>
            <if test="password != null">
                password,
            </if>
            <if test="email != null">
                email,
            </if>
            <if test="mobile != null">
                mobile,
            </if>
            <if test="nickname != null">
                nickname,
            </if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <if test="username != null">
                #{username,jdbcType=VARCHAR},
            </if>
            <if test="password != null">
                #{password,jdbcType=VARCHAR},
            </if>
            <if test="email != null">
                #{email,jdbcType=VARCHAR},
            </if>
            <if test="mobile != null">
                #{mobile,jdbcType=VARCHAR},
            </if>
            <if test="nickname != null">
                #{nickname,jdbcType=VARCHAR},
            </if>
        </trim>
    </insert>
    <update id="updateByPrimaryKeySelective" parameterType="com.zsx.entity.User">
        update t_user
        <set>
            <if test="username != null">
                username = #{username,jdbcType=VARCHAR},
            </if>
            <if test="password != null">
                password = #{password,jdbcType=VARCHAR},
            </if>
            <if test="email != null">
                email = #{email,jdbcType=VARCHAR},
            </if>
            <if test="mobile != null">
                mobile = #{mobile,jdbcType=VARCHAR},
            </if>
            <if test="nickname != null">
                nickname = #{nickname,jdbcType=VARCHAR},
            </if>
        </set>
        where id = #{id,jdbcType=BIGINT}
    </update>
    <update id="updateByPrimaryKey" parameterType="com.zsx.entity.User">
    update t_user
    set username = #{username,jdbcType=VARCHAR},
      password = #{password,jdbcType=VARCHAR},
      email = #{email,jdbcType=VARCHAR},
      mobile = #{mobile,jdbcType=VARCHAR},
      nickname = #{nickname,jdbcType=VARCHAR}
    where id = #{id,jdbcType=BIGINT}
  </update>

</mapper>
```

- UserMapper.java
```
package com.zsx.dao;

import com.zsx.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

  User selectByPrimaryKey(Long id);

  int deleteByPrimaryKey(Long id);

  int insert(User user);

  int insertSelective(User user);

  int updateByPrimaryKeySelective(User user);

  int updateByPrimaryKey(User user);

}
```




















```
```
```
```



> 参考文章：
> [Mybatis Mapper.xml继承机制 - 颇忒脱 - SegmentFault 思否](https://segmentfault.com/a/1190000012470056?utm_source=tuicool&utm_medium=referral#articleHeader1)





---
