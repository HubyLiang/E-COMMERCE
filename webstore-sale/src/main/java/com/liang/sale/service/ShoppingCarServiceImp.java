package com.liang.sale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.sale.bean.T_MALL_SHOPPINGCAR;
import com.liang.sale.bean.T_MALL_USER;
import com.liang.sale.dao.ShoppingCarMapper;

@Service
public class ShoppingCarServiceImp implements ShoppingCarService {
	
	@Autowired
	private ShoppingCarMapper shoppingCarMapper;

	@Override
	public void add_car(T_MALL_SHOPPINGCAR shoppingcar) {
		int i = shoppingCarMapper.insert_car(shoppingcar);
	}

	@Override
	public void update_car(T_MALL_SHOPPINGCAR shoppingcar) {
		int i = shoppingCarMapper.update_car(shoppingcar);	
	}

	@Override
	public List<T_MALL_SHOPPINGCAR> get_shoppingCar_by_user(T_MALL_USER user) {
		
		List<T_MALL_SHOPPINGCAR> list_car = shoppingCarMapper.select_shoppingCar_by_user(user);
		
		return list_car;
	}
	
}
