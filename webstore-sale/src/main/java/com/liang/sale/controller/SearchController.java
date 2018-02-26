package com.liang.sale.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liang.sale.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.liang.sale.bean.OBJECT_T_MALL_ATTR;
import com.liang.sale.bean.OBJECT_T_MALL_SKU;
import com.liang.sale.bean.T_MALL_SKU;
import com.liang.sale.bean.T_MALL_SKU_ATTR_VALUE;
import com.liang.sale.bean.T_MALL_TRADE_MARK;
import com.liang.sale.service.SearchServiceImp;

@Controller
public class SearchController {

	@Autowired
	SearchServiceImp searchServiceImp;

	/**
	 * 商品分类检索
	 * 
	 * @param class_2_id
	 * @param map
	 * @return
	 */
	@RequestMapping("goto_search_index/{class_2_id}/{class_2_name}")
	public String search_index(@PathVariable Integer class_2_id, @PathVariable String class_2_name, ModelMap map) {

		// List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value = new
		// ArrayList<T_MALL_SKU_ATTR_VALUE>();

		List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value = null;

		List<OBJECT_T_MALL_SKU> list_object_sku = searchServiceImp.get_sku_by_class_2_id_and_attr_value(class_2_id,
				list_sku_attr_value, "", 0);

		List<OBJECT_T_MALL_ATTR> list_object_attr = searchServiceImp.get_attr_by_class_2_id(class_2_id);

		List<T_MALL_TRADE_MARK> list_tm = searchServiceImp.get_tm_by_class_2_id(class_2_id);

		map.put("list_object_sku", list_object_sku);
		map.put("list_object_attr", list_object_attr);
		map.put("list_tm", list_tm);

		return "sale_search_index";
	}

	// /**
	// * 商品分类属性检索 方法一
	// *
	// * @param class_2_id
	// * @param map
	// * @return
	// */
	// @RequestMapping("search_index_attr_value")
	// public String search_index_attr_value(Integer class_2_id,
	// @RequestParam(value="list_attr_value[]") String[]
	// list_sku_attr_value,String order, ModelMap map) {
	//
	//// List<OBJECT_T_MALL_SKU> list_object_sku =
	// searchServiceImp.get_sku_by_class_2_id_and_attr_value(class_2_id,
	//// list_sku_attr_value, order, 0);
	////
	//// map.put("list_object_sku", list_object_sku);
	//
	// return "sale_search_index_inner";
	// }

	/**
	 * 商品分类属性检索 方法二
	 * 
	 * @param class_2_id
	 * @param map
	 * @return
	 */
	@RequestMapping("search_index_attr_value")
	public String search_index_attr_value(Integer class_2_id, MODEL_T_MALL_SKU_ATTR_VALUE list_sku_attr_value,
			String order, ModelMap map) {

		List<OBJECT_T_MALL_SKU> list_object_sku = searchServiceImp.get_sku_by_class_2_id_and_attr_value(class_2_id,
				list_sku_attr_value.getList_attr_value(), order, 0);

		map.put("list_object_sku", list_object_sku);
		return "sale_search_index_inner";
	}

	@RequestMapping(value = "go_sku_detail/{sku_id}/{shp_id}")
	public String go_sku_detail(@PathVariable int sku_id, @PathVariable int shp_id,ModelMap map) {
		
		OBJECT_T_MALL_SKU object_sku =  searchServiceImp.get_object_sku_by_sku_id(sku_id);
		List<T_MALL_SKU> list_sku = searchServiceImp.get_sku_list_by_shp_id(shp_id);
		
		map.put("object_sku", object_sku);
		map.put("list_sku", list_sku);
		
		return "sale_search_sku_detail";
	}

	/*	*//**
			 * 商品分类属性检索
			 * 
			 * @param class_2_id
			 * @param map
			 * @return
			 *//*
			 * @RequestMapping("search_index_attr_value") public String
			 * search_index_attr_value(@PathVariable Integer class_2_id,
			 * List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value,String order,
			 * ModelMap map) {
			 * 
			 * List<OBJECT_T_MALL_SKU> list_object_sku =
			 * searchServiceImp.get_sku_by_class_2_id_and_attr_value(class_2_id,
			 * list_sku_attr_value, order, 0);
			 * 
			 * map.put("list_object_sku", list_object_sku);
			 * 
			 * return "sale_search_index_inner"; }
			 */

}
