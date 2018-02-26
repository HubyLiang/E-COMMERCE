package com.liang.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.liang.user.bean.T_MALL_USER;
import com.liang.user.dao.RegisterMapper;

public class RegisterServiceImpl implements RegisterService{
	
	@Autowired
	private RegisterMapper registerMapper;

	@Override
	public String register(T_MALL_USER user) {
		
		registerMapper.insert_user(user);
		
		return "success";
	}

}
