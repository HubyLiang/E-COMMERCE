package com.liang.user.service;

import javax.jws.WebService;

import com.liang.sale.bean.T_MALL_USER;


@WebService
public interface UserService {

	public T_MALL_USER login(T_MALL_USER user);
	
}
