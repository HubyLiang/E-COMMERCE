package com.liang.sale.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;

import net.sf.json.JSONArray;

public class MyJsonUtil {

	/**
	 * 集合转字符串
	 * 
	 * @param list_car
	 * @return
	 */
	public static <T> String list_to_json(List<T> list_car) {
		// 将list_car转化为json字符串
		Gson gson = new Gson();
		String json = gson.toJson(list_car);

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 字符串转集合
	 * 
	 * @param json
	 * @param t
	 */
	public static <T> List<T> json_to_list(String json, Class<T> t) {
		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONArray fromObject = JSONArray.fromObject(json);
		List<T> collection = (List<T>) JSONArray.toCollection(fromObject, t);
		return collection;
	}
}
