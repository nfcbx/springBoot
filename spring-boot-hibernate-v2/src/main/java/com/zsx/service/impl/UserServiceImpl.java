package com.zsx.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zsx.dao.TUserDao;
import com.zsx.entity.Tuser;
import com.zsx.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private TUserDao userdao;
	
	@Override
	public Tuser findByName(String username) {
		return userdao.findByUserName(username);
	}

	@Override
	public void addUser(Tuser user) {
		userdao.save(user);
	}

}
