package com.zsx.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zsx.service.TestService;
import com.zsx.service.impl.TestServiceImpl;

@Configuration
@EnableScheduling // 启用定时配置
public class TimeTask {

	/**
	 * @Scheduled 参数可以接受两种定时的设置，
	 * 一种是我们常用的cron="* /6 * * * * ?",
	 * 一种是 fixedRate = 6000，两种都表示每隔六秒打印一下内容。
	 */
	@Scheduled(cron = "0/60 * * * * ?")
	public void name() {
//		TestServiceImpl bean = (TestServiceImpl) SpringUtil.getBean("testService");
		
//		TestService bean = SpringUtil.getBean(TestService.class);
		
//		TestService bean = SpringUtil.getBean("testService", TestService.class);
		
//		TestService bean = SpringUtil.getBean(TestServiceImpl.class);
		
		TestService bean = SpringUtil.getBean("testService", TestServiceImpl.class);
		
		bean.gettime();
		
	}
	
	/*
	 * 
	 * fixedRate 说明

		@Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
		@Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
		@Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按fixedRate的规则每6秒执行一次

	 */
}
