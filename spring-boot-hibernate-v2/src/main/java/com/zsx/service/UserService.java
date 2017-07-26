package com.zsx.service;

import com.zsx.entity.Tuser;

public interface UserService {
	Tuser findByName(String username);
	
	void addUser(Tuser user);
}
