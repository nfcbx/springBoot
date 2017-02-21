package com.zsx.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zsx.service.TestService;
import com.zsx.service.impl.TestServiceImpl;

@Configuration
@EnableScheduling
public class TimeTask {

	
	@Scheduled(cron = "0/3 * * * * ?")
	public void name() {
//		TestServiceImpl bean = (TestServiceImpl) SpringUtil.getBean("testService");
		
//		TestService bean = SpringUtil.getBean(TestService.class);
		
//		TestService bean = SpringUtil.getBean("testService", TestService.class);
		
//		TestService bean = SpringUtil.getBean(TestServiceImpl.class);
		
		TestService bean = SpringUtil.getBean("testService", TestServiceImpl.class);
		
		bean.asdf();
		
	}
}
