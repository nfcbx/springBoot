package com.zsx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zsx.util.ExecutorUtil;

@RestController
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String name() {
		return "Hello zhao!";
	}
	
	@GetMapping(value = {"/a", "/b", "/c"})
	public String name2(HttpServletRequest request) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Hello " + request.getServletPath();
	}
	
	
	@GetMapping(value = {"/aa"})
	public Object name3(HttpServletRequest request, @RequestParam(value = "name", defaultValue="123") String name) {
		JSONObject object = new JSONObject();
		
		
		ExecutorUtil.executor.execute(new Runnable() {
			
			@Override
			public void run() {
				object.put("code", 200);
				object.put("message", "阿斯蒂芬加拉设计费拉丝机枫蓝国际啦爱神的箭法拉时代峻峰两份礼物而");
				object.put("data", "{\"list\":[{\"id\":80,\"email\":\"zhanglin@qiand.me\"}]}");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		return object;
	}
	
	
	
	
}
