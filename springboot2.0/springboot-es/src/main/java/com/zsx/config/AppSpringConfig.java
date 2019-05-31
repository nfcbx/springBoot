package com.zsx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaoshuxue3
 * @Date 2019/5/30 18:45
 **/
@Configuration
@ImportResource({"classpath:spring/spring.xml"})
public class AppSpringConfig implements WebMvcConfigurer {





}
