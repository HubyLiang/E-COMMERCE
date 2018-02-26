package com.liang.user.dao;

import java.util.List;

import com.liang.user.bean.T_MALL_USER;


public interface LoginMapper {

	public T_MALL_USER select_user(T_MALL_USER user);
	
}
