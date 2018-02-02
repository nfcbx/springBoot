package com.zsx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
//@EnableAutoConfiguration
//加上这行注释避免没有引入数据库驱动，导致工程起不来的问题
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class App{

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
