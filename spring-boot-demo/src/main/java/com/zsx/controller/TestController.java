package com.zsx.controller;

import com.alibaba.fastjson.JSONObject;
import com.zsx.config.ConfigProperties;
import com.zsx.util.ExecutorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

	@Autowired
	private ConfigProperties configProperties;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String name() {

		System.out.println(configProperties.toString());
		System.out.println(configProperties.getAdditionalHeaders());
		System.out.println(configProperties.getCredentials());
		System.out.println(configProperties.getCs());
		System.out.println(configProperties.getMp());
		System.out.println(configProperties.getRecipients());

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
		final JSONObject object = new JSONObject();
		
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
