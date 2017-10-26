package com.zsx;

import javax.swing.JFrame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App extends JFrame{

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
