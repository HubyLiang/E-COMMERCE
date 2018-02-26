package com.liang.sale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.liang.sale.bean.T_MALL_USER;

@Controller
public class IndexController {
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpSession session){
		Cookie[] cookies = request.getCookies();
		String sale_cookie_user = "";
		
		if(cookies != null){
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("sale_cookie_user")){
					sale_cookie_user = cookies[i].getValue();
				}
			}
			
			try {
				sale_cookie_user = URLDecoder.decode(sale_cookie_user,"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			if(sale_cookie_user!=null&&!sale_cookie_user.equals("")){
				Gson gson = new Gson();
				T_MALL_USER fromJson = gson.fromJson(sale_cookie_user, T_MALL_USER.class);
				session.setAttribute("user", fromJson);
			}
		}
		return "sale_index";
	}
}
