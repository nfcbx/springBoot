package com.zsx.springbootkisso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class
})
public class SpringbootKissoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKissoApplication.class, args);
    }

}
