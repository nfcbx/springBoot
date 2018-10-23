package com.zsx.controller;

import com.zsx.config.ErrorMessage;
import com.zsx.entity.User;
import com.zsx.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@Api(description = "接口描述")
@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "干嘛的", httpMethod = "GET", notes = "说明文字")
    @GetMapping("/userList")
    public List<User> getUserList() {
        return userService.getAll();
    }


    @ApiOperation(value = "添加验证", httpMethod = "GET", notes = "说明文字")
    @GetMapping("/users")
    public List<User> getUsers(
            @RequestParam("name")
            @Size(min = 3, max = 10, message = ErrorMessage.USERNAME_LEN_ILLEGAL)
                    String name,
            @RequestParam("email")
            @Email(message = ErrorMessage.EMAIL_ILLEGAL)
                    String email,
            @NotBlank
            @RequestParam("address")
                    String address
    ) {
        return userService.getAll();
    }

}
