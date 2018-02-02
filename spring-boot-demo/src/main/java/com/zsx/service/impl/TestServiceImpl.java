package com.zsx.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.zsx.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Override
	public void gettime() {
//		
//		Connection connection = null;
//		
//		connection.prepareCall("{call procName (1,2,3)}");
		
		System.out.println(Calendar.getInstance().getTime());
		
	}

}
