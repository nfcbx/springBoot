package com.zsx.controller;

import com.zsx.entity.User;
import com.zsx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@Api(description = "接口描述")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "干嘛的", httpMethod = "GET", notes = "说明文字")
    @GetMapping("/users")
    public List<User> getUserList() {
        return userService.getAll();
    }


}
