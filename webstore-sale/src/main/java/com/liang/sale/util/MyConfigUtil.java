package com.liang.sale.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.liang.sale.bean.T_MALL_SHOPPINGCAR;

public class MyConfigUtil {

	public static String getProperties(String key) {
		Properties properties = new Properties();
		InputStream inputStream = MyConfigUtil.class.getClassLoader().getResourceAsStream("myConfigUtil.properties");

		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String property = properties.getProperty(key);
		return property;
	}

	/**
	 * 判断是否是新的购物车
	 * 
	 * @param shoppingcar
	 * @param list_car
	 * @return
	 */
	public static boolean is_new_car_item(T_MALL_SHOPPINGCAR shoppingcar, List<T_MALL_SHOPPINGCAR> list_car) {
		boolean isNew = true;
		for (int i = 0; i < list_car.size(); i++) {
			if (list_car.get(i).getSku_id() == shoppingcar.getSku_id()) {
				isNew = false;
			}
		}
		return isNew;
	}

	/**
	 * 
	 * @param shoppingcar
	 * @param list_car
	 * @return
	 */
	public static Map<Object, Object> isNewCarMap(T_MALL_SHOPPINGCAR shoppingcar, List<T_MALL_SHOPPINGCAR> list_car) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("flag", true);

		for (int i = 0; i < list_car.size(); i++) {
			if (list_car.get(i).getSku_id() == shoppingcar.getSku_id()) {
				map.put("flag", false);
				map.put("shoppingcar", list_car.get(i));
			}
		}
		return map;
	}
}
