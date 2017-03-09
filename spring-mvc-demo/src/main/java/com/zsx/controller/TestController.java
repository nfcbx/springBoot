package com.zsx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	
	@RequestMapping("/home")
	public String aaaa(){
		return "index";
	}
	
	@RequestMapping("/demo")
	@ResponseBody
	public String test(){
		return "fsdf";
	}

}
