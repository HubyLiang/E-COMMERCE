package com.liang.sale.service;

import java.util.List;

import com.liang.sale.bean.T_MALL_SHOPPINGCAR;
import com.liang.sale.bean.T_MALL_USER;

public interface ShoppingCarService {

	void add_car(T_MALL_SHOPPINGCAR shoppingcar);

	void update_car(T_MALL_SHOPPINGCAR shoppingcar);

	List<T_MALL_SHOPPINGCAR> get_shoppingCar_by_user(T_MALL_USER user);

}
