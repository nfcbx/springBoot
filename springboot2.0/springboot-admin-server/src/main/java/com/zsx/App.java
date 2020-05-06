package com.zsx;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer // 启动监控
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
