package com.liang.sale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.liang.sale.bean.T_MALL_SHOPPINGCAR;
import com.liang.sale.bean.T_MALL_USER;
import com.liang.sale.service.LoginServiceImp;
import com.liang.sale.service.ShoppingCarService;
import com.liang.sale.util.MyConfigUtil;
import com.liang.sale.util.MyJsonUtil;
import com.liang.sale.util.MyWsFactory;
import com.liang.user.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private ShoppingCarService shoppingCarService;

	@RequestMapping("goto_login")
	public String goto_login() {
		return "sale_login";
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, String auto_login, T_MALL_USER user,
			HttpSession session, @CookieValue(value = "cookie_list_car", required = false) String cookie_list_car,
			ModelMap map) {
		
		T_MALL_USER userLogin = loginService.login(user); //调用 WS 认证接口

		if (userLogin != null) {
			if (auto_login != null) {
				auto_login(userLogin, response);
			}
			session.setAttribute("user", userLogin);

			// 同步cookie和db中的购物车数据
			cookie_car_to_db_session(userLogin, cookie_list_car, response, session);

			return "redirect:/index.do";
		} else {
			map.put("err", "用户名或密码错误");
			return "sale_login";
		}
	}

	/**
	 * 将cookie中的购物车数据同步到数据库和session中
	 * 
	 * @param userLogin
	 * @param request
	 * @param response
	 */
	private void cookie_car_to_db_session(T_MALL_USER userLogin, String cookie_list_car, HttpServletResponse response,
			HttpSession session) {

		// 从db中查询购物车数据
		List<T_MALL_SHOPPINGCAR> list_car = shoppingCarService.get_shoppingCar_by_user(userLogin);

		if (cookie_list_car == null || cookie_list_car.equals("")) {
			// cookie中没有购物车数据,不进行任何操作
		} else {
			// cookie中购物项转为list
			List<T_MALL_SHOPPINGCAR> list_from_cookie = MyJsonUtil.json_to_list(cookie_list_car,
					T_MALL_SHOPPINGCAR.class);

			for (int i = 0; i < list_from_cookie.size(); i++) {
				list_from_cookie.get(i).setYh_id(userLogin.getId());
				// db中有购物车数据,根据判断执行插入或者跟新操作
				if (list_car != null && list_car.size() != 0) {
					Map<Object, Object> carMap = MyConfigUtil.isNewCarMap(list_from_cookie.get(i), list_car);

					T_MALL_SHOPPINGCAR shoppingcar = (T_MALL_SHOPPINGCAR) carMap.get("shoppingcar");
					boolean isNew = (boolean) carMap.get("flag");

					if (isNew) {
						shoppingCarService.add_car(list_from_cookie.get(i));
					} else {
						// 遍历db中的购物车集合, 找到需要更新的购物项
						list_from_cookie.get(i).setId(shoppingcar.getId());
						list_from_cookie.get(i).setTjshl(list_from_cookie.get(i).getTjshl() + shoppingcar.getTjshl());
						list_from_cookie.get(i)
								.setHj(list_from_cookie.get(i).getTjshl() * list_from_cookie.get(i).getSku_jg());
						
						shoppingCarService.update_car(list_from_cookie.get(i));
					}
				} else {
					// db中没有数据,直接插入cookie中的购物车数据
					shoppingCarService.add_car(list_from_cookie.get(i));
				}
			}
		}

		list_car = shoppingCarService.get_shoppingCar_by_user(userLogin);
		// 同步session
		session.setAttribute("session_list_car", list_car);
		// 清空cookie
		response.addCookie(new Cookie("cookie_list_car", ""));
	}

	/**
	 * 用户自动登录
	 * 
	 * @param user
	 * @param response
	 */
	public void auto_login(T_MALL_USER user, HttpServletResponse response) {
		Gson gson = new Gson();
		String json = gson.toJson(user);
		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie cookie = new Cookie("sale_cookie_user", json);
		cookie.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie);
	}

	@RequestMapping("goto_logout")
	public String goto_logout(HttpSession session, HttpServletResponse response) {
		response.addCookie(new Cookie("sale_cookie_user", ""));
		session.invalidate();
		return "redirect:/index.do";
	}

	@RequestMapping("goto_regist")
	public String goto_regist() {
		return "sale_regist";
	}

}
