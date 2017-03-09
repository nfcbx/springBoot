package com.zsx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TTController {
	
	@GetMapping("demo2")
	public String asdf(){
		
		return "wohenhao";
	}

}
