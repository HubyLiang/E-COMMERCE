package com.liang.sale.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liang.sale.bean.T_MALL_ADDRESS;
import com.liang.sale.bean.T_MALL_USER;
import com.liang.sale.util.MyWsFactory;
import com.liang.user.service.AddressService;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value="/goto_add_address" ,method=RequestMethod.GET)
	public String goto_add_address(ModelMap map,HttpSession session){
		
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		List<T_MALL_ADDRESS> list_address = addressService.get_address_by_user(user);
		
		map.addAttribute("list_address", list_address);
		
		return "sale_address";
	}
	
	@RequestMapping(value="/add_address",method=RequestMethod.POST)
	public String add_address(T_MALL_ADDRESS address){
		
		addressService.add_address(address);
		return "redirect:/goto_add_address.do";
	}
	
}
