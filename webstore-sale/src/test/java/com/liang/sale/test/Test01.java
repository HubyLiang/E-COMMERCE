package com.liang.sale.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.liang.sale.bean.T_MALL_USER;
import com.liang.user.service.UserService;

public class Test01 {

	public static void main(String[] args) {
		
		JaxWsProxyFactoryBean jpb = new JaxWsProxyFactoryBean();
		
		jpb.setAddress("http://localhost:28080/maven_mall_user/userService");
		
		UserService userService = jpb.create(UserService.class);
		
		T_MALL_USER user = new T_MALL_USER();
		
		user.setYh_mch("test02");
		
		user.setYh_mm("123");
		
		T_MALL_USER user_from_db = userService.login(user);
		
		System.out.println(user_from_db);
		
	}
}
