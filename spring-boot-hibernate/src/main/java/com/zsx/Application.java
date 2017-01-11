package com.zsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class Application {
	
	@RequestMapping("/hello")
	public String hello(){
		return "hello World!!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

