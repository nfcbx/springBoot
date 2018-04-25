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

## 扩展类，mapper继承文件

- UserDao.java

```
package com.zsx.dao;

import com.zsx.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao extends UserMapper {

    List<User> selectByParams(@Param("search") Object search);

}
```

- TuserMapperDao.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.zsx.dao.UserDao">
    <resultMap id="BaseResultMap" type="com.zsx.entity.User">
        <id column="id" jdbcType="BIGINT" property="id"/>
        <result column="username" jdbcType="VARCHAR" property="username"/>
        <result column="password" jdbcType="VARCHAR" property="password"/>
        <result column="email" jdbcType="VARCHAR" property="email"/>
        <result column="mobile" jdbcType="VARCHAR" property="mobile"/>
        <result column="nickname" jdbcType="VARCHAR" property="nickname"/>
    </resultMap>


    <sql id="Base_Column_List">
        username, password
    </sql>

    <sql id="Base_Where_Clause">
        <where>
            1=1
            <if test="search.username != null">
                and username = #{search.username,jdbcType=VARCHAR}
            </if>
            <if test="search.isDel != null">
                and is_del = #{search.isDel,jdbcType=INTEGER}
            </if>
        </where>
    </sql>

    <select id="selectByParams" resultMap="BaseResultMap">
        SELECT
        <include refid="Base_Column_List"/>
        FROM t_user
        <include refid="Base_Where_Clause"/>
    </select>
</mapper>
```

1. Mapper.xml继承机制只针对statement有效，对于sql、resultMap是无效的。
2. > 规律可以总结为：

   > 1). ParentMapper.xml中有，ChildMapper.xml中没有，ChildMapper沿用ParentMapper.xml中的定义

   > 2). ParentMapper.xml中有，ChildMapper.xml中也有，ChildMapper使用ChildMapper.xml中的定义

   > 3). ParentMapper.xml中没有，ChildMapper.xml中有，ChildMapper使用ChildMapper.xml中的定义

3. 后续修改字段等操作后，可以使用工具直接替换 UserMapper.java 和 TuserMapper.xml 这两个文件，尽量减少了文件的比较和解决冲突

## 公共的mapper文件

我们还可以抽出一个有公共方法的mapper文件，这样就不需要每次都写名扩展的自定义方法了，同时这些方法在mapper.xml里面没有实现也是没有问题的。

- BaseDao.java

```
package com.zsx.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by highness on 2018/3/9 0009.
 */
public interface BaseDao<E> {
    /**mybatis-generator:generate begin***/
    E selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(E entity);

    int insertSelective(E entity);

    int updateByPrimaryKeySelective(E entity);

    int updateByPrimaryKey(E entity);
    /**mybatis-generator:generate end***/

    /**extended definition begin***/
    int insertBatch(List<E> entityList);

    int deleteBatchByPrimaryKey(@Param("search") Object search);

    int updateByParamsSelective(@Param("entity") E entity, @Param("search") Object search);

    int countByParams(@Param("search") Object search);

    List<Long> selectForPrimaryKey(@Param("search") Object search);

    List<E> selectByParams(@Param("search") Object search);
    /**extended definition end***/
}
```

使用时只需在对应的mapper里面继承baseDao即可，如下：

```
@Mapper
public interface UserMapper extends BaseDao<User>{
}
```


---

具体代码可以去github上clone，地址为：

https://github.com/zhaoshuxue/springBoot/tree/master/example/springboot-mybatis-xml-extends





> 参考文章：
> [Mybatis Mapper.xml继承机制 - 颇忒脱 - SegmentFault 思否](https://segmentfault.com/a/1190000012470056?utm_source=tuicool&utm_medium=referral#articleHeader1)





---
