package com.liang.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.liang.user.bean.T_MALL_USER;
import com.liang.user.dao.LoginMapper;

public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public T_MALL_USER login(T_MALL_USER user) {
		
		T_MALL_USER user_from_db = loginMapper.select_user(user);
		
		return user_from_db;
	}

}
