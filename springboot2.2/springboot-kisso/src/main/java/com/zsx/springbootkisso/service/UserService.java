package com.zsx.springbootkisso.service;

import com.alibaba.fastjson.JSONObject;
import com.zsx.springbootkisso.entity.Tuser;

public interface UserService {

    Tuser getUser(String username);

    String getUser(Integer id);

    JSONObject addUser(String username, String password);

}
