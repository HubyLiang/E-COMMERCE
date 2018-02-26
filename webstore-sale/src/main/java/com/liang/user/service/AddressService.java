package com.liang.user.service;


import java.util.List;

import javax.jws.WebService;

import com.liang.sale.bean.T_MALL_ADDRESS;
import com.liang.sale.bean.T_MALL_USER;


@WebService
public interface AddressService {

	public String add_address(T_MALL_ADDRESS address);
	
	public List<T_MALL_ADDRESS> get_address_by_user(T_MALL_USER user);
	
}
