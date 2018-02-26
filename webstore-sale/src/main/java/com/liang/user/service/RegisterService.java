package com.liang.user.service;

import javax.jws.WebService;

import com.liang.sale.bean.T_MALL_USER;

@WebService
public interface RegisterService {
	
	public String register(T_MALL_USER user);

}
