package com.zsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {
	
	@GetMapping(value = {"/", "/123"})
	public String name() {
		return "123";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
