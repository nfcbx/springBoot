package com.zsx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String name() {
		return "Hello zhao!";
	}
	
	@GetMapping(value = {"/a", "/b", "/c"})
	public String name2(HttpServletRequest request) {
		
		return "Hello " + request.getServletPath();
	}
	
	
	
	
	
	
}
