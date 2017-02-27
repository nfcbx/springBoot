package com.zsx.service.impl;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.zsx.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Override
	public void gettime() {
		System.out.println(Calendar.getInstance().getTime());
		
	}

}
