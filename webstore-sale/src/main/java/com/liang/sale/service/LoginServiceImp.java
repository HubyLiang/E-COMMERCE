package com.liang.sale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.sale.bean.T_MALL_USER;
import com.liang.sale.dao.LoginMapper;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	public T_MALL_USER login(T_MALL_USER user) {
		T_MALL_USER userLogin = loginMapper.select_user(user);
		return userLogin;
	}

}
