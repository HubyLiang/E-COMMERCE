package com.liang.sale.dao;

import java.util.List;

import com.liang.sale.bean.T_MALL_SHOPPINGCAR;
import com.liang.sale.bean.T_MALL_USER;

public interface ShoppingCarMapper {

	int insert_car(T_MALL_SHOPPINGCAR shoppingcar);

	int update_car(T_MALL_SHOPPINGCAR shoppingcar);

	List<T_MALL_SHOPPINGCAR> select_shoppingCar_by_user(T_MALL_USER user);

}
