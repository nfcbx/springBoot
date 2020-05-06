package com.zsx.springbootkisso.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsx.springbootkisso.entity.Tuser;
import com.zsx.springbootkisso.service.UserService;
import com.zsx.springbootkisso.utils.SecureUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Tuser getUser(String username) {

        String sql = "select id, username, password, gender, birthday, last_login_time as lastLoginTime, " +
                "last_login_ip as lastLoginIp, user_level as userLevel, nickname, mobile, avatar, status, " +
                "create_time as createTime, update_time as updateTime, deleted from t_user where deleted = 0 and username = '" + username + "'";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        logger.info("根据username= {} 查询用户表，结果为：{}", username, JSON.toJSONString(list));
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Object> map = list.get(0);

            Tuser tuser = JSONObject.parseObject(JSON.toJSONString(map), Tuser.class);
            String password = tuser.getPassword();
            if (StringUtils.isNotBlank(password)) {
                String decrypt = SecureUtil.decrypt(password);
                tuser.setPassword(decrypt);
            }
            return tuser;
        }
        return null;
    }

    @Override
    public String getUser(Integer id) {
        String sql = "select id, username, gender, birthday, last_login_time as lastLoginTime, " +
                "last_login_ip as lastLoginIp, user_level as userLevel, nickname, mobile, avatar, status " +
                " from t_user where deleted = 0 and id = ?";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, id);
        logger.info("根据主键id= {} 查询用户表，结果为：{}", id, JSON.toJSONString(list));
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Object> map = list.get(0);
            return JSON.toJSONString(map);
        }
        return null;
    }

    @Override
    public JSONObject addUser(String username, String password) {
        JSONObject json = new JSONObject();
        String sql = "select id, username from t_user where deleted = 0 and username = '" + username + "'";

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        logger.info("根据username= {} 查询用户表，结果为：{}", username, JSON.toJSONString(list));
        if (CollectionUtils.isNotEmpty(list)) {
            json.put("success", false);
            json.put("message", "用户名已存在，请更换后重试");
            return json;
        }

        password = SecureUtil.encrypt(password);

        sql = "INSERT INTO `t_user` (`username`, `password`) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, password);

        json.put("success", true);
        json.put("message", "注册成功");
        return json;
    }
}
