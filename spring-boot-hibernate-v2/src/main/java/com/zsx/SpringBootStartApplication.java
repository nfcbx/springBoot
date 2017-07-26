package com.zsx;

import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 * @author ZSX
 * tips: springboot 1.5.x版本后
 * import org.springframework.boot.context.web.SpringBootServletInitializer;
 * 改为
 * import org.springframework.boot.web.support.SpringBootServletInitializer;
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

}
