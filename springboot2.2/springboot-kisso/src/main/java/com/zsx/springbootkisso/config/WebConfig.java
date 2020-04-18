package com.zsx.springbootkisso.config;

import com.baomidou.kisso.web.interceptor.SSOSpringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ControllerAdvice
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static String[] urls = new String[]{
            "/js/**", "/css/**", "/images/**",
            "/",
            "/index",
            "/login",
            "/register",
            "/forget"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SSOSpringInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(urls);
    }
}
