package com.zsx.controller;

import com.github.pagehelper.PageInfo;
import com.zsx.entity.MonitorHeartbeatLog;
import com.zsx.entity.User;
import com.zsx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUserList() {
        return userService.getAll();
    }

    @GetMapping("/users2")
    public List<User> getUserList2() {
        return userService.getAllSlave();
    }

    @GetMapping("logs")
    public PageInfo getLogs(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<MonitorHeartbeatLog> pageInfo = userService.getLogPageList(map, pageNum, pageSize);
        return pageInfo;
    }

    @GetMapping("logs2")
    public PageInfo getLogs2(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageInfo<MonitorHeartbeatLog> pageInfo = userService.getLogPageListSlave(map, pageNum, pageSize);
        return pageInfo;
    }
}
