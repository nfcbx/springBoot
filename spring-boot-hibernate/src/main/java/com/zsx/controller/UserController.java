package com.zsx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.zsx.entity.Tuser;
import com.zsx.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String helloWorld() {
		return "hello World";
	}
	
	/**
	 * 测试地址  http://localhost:8080/getUser?name=zhao
	 * @param name
	 * @return
	 */
	@RequestMapping("/getUser")
	@ResponseBody
	public String user(String name) {
		Tuser user = userService.findByName(name);
		return JSON.toJSONString(user);
	}
	
	/**
	 * 测试地址 http://localhost:8080/addUser?userName=zhao
	 * @param user
	 * @return
	 */
	@RequestMapping("/addUser")
	@ResponseBody
	public String adduser(Tuser user) {
		userService.addUser(user);
		return "add success!";
	}
}
