package com.liang.sale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.liang.sale.bean.T_MALL_SHOPPINGCAR;
import com.liang.sale.bean.T_MALL_USER;
import com.liang.sale.service.ShoppingCarService;
import com.liang.sale.util.MyJsonUtil;

@Controller
public class ShoppingCarController {

	@Autowired
	private ShoppingCarService shoppingCarService;

	@RequestMapping(value = "shoppingCar_change", method = RequestMethod.POST)
	public String shoppingCar_change(HttpSession session, int car_id, int sku_id, String shfxz, int tjshl, ModelMap map,
			@CookieValue(value = "cookie_list_car", required = false) String cookie_list_car,
			HttpServletResponse response) {
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_car = null;

		if (tjshl > 0) {
			// 改变数量
			if (user != null) {
				// 改变数据库和session
				list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");
				
				for (int i = 0; i < list_car.size(); i++) {
					if (list_car.get(i).getId() == car_id) {
						list_car.get(i).setTjshl(tjshl);
						list_car.get(i).setHj(list_car.get(i).getTjshl() * list_car.get(i).getSku_jg());
						
						shoppingCarService.update_car(list_car.get(i));
					}
				}
			} else {
				// 改变cookie
				list_car = MyJsonUtil.json_to_list(cookie_list_car, T_MALL_SHOPPINGCAR.class);
				for (int i = 0; i < list_car.size(); i++) {
					if (sku_id == list_car.get(i).getSku_id()) {
						list_car.get(i).setTjshl(tjshl);
						list_car.get(i).setHj(list_car.get(i).getTjshl() * list_car.get(i).getSku_jg());
					}
				}
				cookie_list_car = MyJsonUtil.list_to_json(list_car);

				Cookie cookie = new Cookie("cookie_list_car", cookie_list_car);
				cookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(cookie);
			}
		} else {
			// 改变选中状态
			if (user != null) {
				// 改变数据库和session
				list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");
				
				for (int i = 0; i < list_car.size(); i++) {
					if (list_car.get(i).getId() == car_id) {
						list_car.get(i).setShfxz(shfxz);
						
						shoppingCarService.update_car(list_car.get(i));
					}
				}
			} else {
				// 改变cookie
				list_car = MyJsonUtil.json_to_list(cookie_list_car, T_MALL_SHOPPINGCAR.class);
				for (int i = 0; i < list_car.size(); i++) {
					if (sku_id == list_car.get(i).getSku_id()) {
						list_car.get(i).setShfxz(shfxz);
					}
				}

				cookie_list_car = MyJsonUtil.list_to_json(list_car);

				Cookie cookie = new Cookie("cookie_list_car", cookie_list_car);
				cookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(cookie);
			}
		}

		double sum = 0;
		for (int i = 0; i < list_car.size(); i++) {
			if (list_car.get(i).getShfxz().equals("1")) {
				sum += list_car.get(i).getHj();
			}
		}
		
		map.put("sum", sum);
		map.put("list_car", list_car);
		return "sale_shoppingCar_inner";
	}

	@RequestMapping(value = "/goto_shoppingCar_success/{sku_mch}/{sku_id}", method = RequestMethod.GET)
	public String goto_shoppingCar_success(@PathVariable String sku_mch, @PathVariable int sku_id) {

		return "sale_shoppingCar_success";
	}

	@RequestMapping("goto_shoppingCar")
	public String goto_shoppingCar(HttpSession session,
			@CookieValue(value = "cookie_list_car", required = false) String cookie_list_car, ModelMap map) {

		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_car = null;
		double sum = 0;
		
		// 根据用户是否登录决定是取session 还是 cookie
		if (user != null) {
			list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");
		} else {
			if (!cookie_list_car.equals("")) {
				list_car = MyJsonUtil.json_to_list(cookie_list_car, T_MALL_SHOPPINGCAR.class);
			}
		}

		if (list_car != null) {
			for (int i = 0; i < list_car.size(); i++) {
				if (list_car.get(i).getShfxz().equals("1")) {
					sum += list_car.get(i).getHj();
				}
			}
		}

		map.put("sum", sum);
		map.put("list_car", list_car);
		return "sale_shoppingCar";
	}

	/**
	 * 添加购物车
	 * 
	 * @param shoppingcar
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add_car", method = RequestMethod.POST)
	public ModelAndView add_car(T_MALL_SHOPPINGCAR shoppingcar, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");

		List<T_MALL_SHOPPINGCAR> list_car = null;
		int count = 0;

		if (user != null) {
			// 对db进行操作

			// 从session中取出购物车集合
			list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");

			if (list_car == null) {
				list_car = shoppingCarService.get_shoppingCar_by_user(user);
			}

			// 判断db中是否有购物数据
			if (list_car == null || list_car.size() == 0) {
				// 直接插入db
				shoppingcar.setYh_id(user.getId());
				shoppingCarService.add_car(shoppingcar);

				// 同步session
				list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
				list_car.add(shoppingcar);

				session.setAttribute("session_list_car", list_car);
			} else {
				shoppingcar.setYh_id(user.getId());
				// 判断是否是新的购物数据
				boolean isNew = is_new_car_item(shoppingcar, list_car);
				if (isNew) {
					// 插入db
					shoppingCarService.add_car(shoppingcar);
					list_car.add(shoppingcar);
					for (T_MALL_SHOPPINGCAR item : list_car) {
						count += item.getTjshl();
					}
				} else {
					// 更新db
					for (int i = 0; i < list_car.size(); i++) {
						if (list_car.get(i).getSku_id() == shoppingcar.getSku_id()) {
							list_car.get(i).setTjshl(list_car.get(i).getTjshl() + shoppingcar.getTjshl());
							list_car.get(i).setHj(list_car.get(i).getTjshl() * list_car.get(i).getSku_jg());

							shoppingcar.setHj(list_car.get(i).getHj());
							shoppingcar.setTjshl(list_car.get(i).getTjshl());
							shoppingcar.setId(list_car.get(i).getId());
						}
						count += list_car.get(i).getTjshl();
					}
					shoppingCarService.update_car(shoppingcar);
				}
			}
		} else {

			Cookie[] cookies = request.getCookies();
			String cookie_list_car = "";
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("cookie_list_car")) {
						cookie_list_car = cookies[i].getValue();
					}
				}
			}

			if (!cookie_list_car.equals("")) {
				list_car = MyJsonUtil.json_to_list(cookie_list_car, T_MALL_SHOPPINGCAR.class); // 将字符串转化为购物车集合
			}
			// 对cookie进行操作
			if (list_car == null) {
				list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
				// 直接插入cookie
				list_car.add(shoppingcar);
				for (T_MALL_SHOPPINGCAR item : list_car) {
					count += item.getTjshl();
				}
			} else {

				// 判断是否是新的购物数据
				boolean isNew = is_new_car_item(shoppingcar, list_car);
				if (isNew) {
					// 插入cookie
					list_car.add(shoppingcar);
				} else {
					// 更新cookie
					for (int i = 0; i < list_car.size(); i++) {
						if (list_car.get(i).getSku_id() == shoppingcar.getSku_id()) {
							list_car.get(i).setTjshl(list_car.get(i).getTjshl() + shoppingcar.getTjshl());
							list_car.get(i).setHj(list_car.get(i).getTjshl() * list_car.get(i).getSku_jg());
						}
						count += list_car.get(i).getTjshl();
					}
				}
			}
			// 更新客户端cookie
			// 将list_car转为json字符串,放入浏览器的cookie中
			cookie_list_car = MyJsonUtil.list_to_json(list_car);

			Cookie cookie = new Cookie("cookie_list_car", cookie_list_car);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/goto_shoppingCar_success/{sku_mch}/{sku_id}.do");
		modelAndView.addObject("sku_mch", shoppingcar.getSku_mch());
		modelAndView.addObject("sku_id", shoppingcar.getSku_id());
		session.setAttribute("count", count);

		return modelAndView;
	}

	/**
	 * 判断是否是新的购物车
	 * 
	 * @param shoppingcar
	 * @param list_car
	 * @return
	 */
	private boolean is_new_car_item(T_MALL_SHOPPINGCAR shoppingcar, List<T_MALL_SHOPPINGCAR> list_car) {
		boolean isNew = true;
		for (int i = 0; i < list_car.size(); i++) {
			if (list_car.get(i).getSku_id() == shoppingcar.getSku_id()) {
				isNew = false;
			}
		}
		return isNew;
	}

	/**
	 * 迷你购物车列表
	 * 
	 * @param session
	 * @param map
	 * @param cookie_list_car
	 * @return
	 */
	@RequestMapping(value = "mini_car", method = RequestMethod.POST)
	public String mini_car(HttpSession session, ModelMap map,
			@CookieValue(value = "cookie_list_car", required = false) String cookie_list_car) {

		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_car = null;
		if (user != null) {
			list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");
		} else {
			if (cookie_list_car != null && !cookie_list_car.equals("")) {
				list_car = MyJsonUtil.json_to_list(cookie_list_car, T_MALL_SHOPPINGCAR.class);
			}
		}

		map.put("list_car", list_car);
		return "sale_mini_shoppingCar_inner";
	}

}
