package com.liang.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.liang.user.bean.T_MALL_ADDRESS;
import com.liang.user.bean.T_MALL_USER;
import com.liang.user.dao.AddressMapper;

public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public String add_address(T_MALL_ADDRESS address) {
		
		addressMapper.insert_address(address);
		return "success";
	}

	@Override
	public List<T_MALL_ADDRESS> get_address_by_user(T_MALL_USER user) {
		
		List<T_MALL_ADDRESS> list_address = addressMapper.select_address_by_user(user);
		return list_address;
	}

}
