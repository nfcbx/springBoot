package com.zsx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("url")
public class URLController {

	@RequestMapping("/demo.do")
	public String name(Map<String,Object> map, HttpServletRequest request) {

//		字符变量		
		map.put("hello","from TemplateController.helloHtml");
		
//		集合
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		
		map.put("list", list);
		
//		对象
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("a", "111");
		map2.put("b", "222");
		map2.put("c", "333");
		
		map.put("obj", map2);
		
		
		
//		session存值
		request.getSession().setAttribute("userId", "userId110");
		
		request.setAttribute("userName", "fuck");
		return "/demo";
	}
}
