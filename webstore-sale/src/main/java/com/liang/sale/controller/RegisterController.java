package com.liang.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liang.sale.bean.T_MALL_USER;
import com.liang.sale.util.MyWsFactory;
import com.liang.user.service.RegisterService;
import com.liang.user.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public String regist(T_MALL_USER user){
		registerService.register(user);
		
		return "sale_login";
	}

}
