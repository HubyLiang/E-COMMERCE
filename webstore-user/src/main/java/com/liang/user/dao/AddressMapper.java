package com.liang.user.dao;

import java.util.List;

import com.liang.user.bean.T_MALL_ADDRESS;
import com.liang.user.bean.T_MALL_USER;


public interface AddressMapper {

	void insert_address(T_MALL_ADDRESS address);

	List<T_MALL_ADDRESS> select_address_by_user(T_MALL_USER user);

	
}
