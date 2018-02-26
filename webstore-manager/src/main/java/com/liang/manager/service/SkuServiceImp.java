package com.liang.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.manager.bean.OBJECT_T_MALL_ATTR;
import com.liang.manager.bean.T_MALL_PRODUCT;
import com.liang.manager.bean.T_MALL_SKU;
import com.liang.manager.bean.T_MALL_SKU_ATTR_VALUE;
import com.liang.manager.dao.SkuMapper;

@Service
public class SkuServiceImp implements SkuService {

	@Autowired
	SkuMapper skuMapper;

	public List<T_MALL_PRODUCT> get_spu_by_class_id_and_tm_id(int class_1_id, int class_2_id, int tm_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("class_1_id", class_1_id);
		map.put("class_2_id", class_2_id);
		map.put("tm_id", tm_id);

		List<T_MALL_PRODUCT> list_spu = skuMapper.select_spu_by_class_id_and_tm_id(map);
		
		return list_spu;
	}

	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id_sku(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_object_attr_value = skuMapper.select_attr_by_class_2_id_sku(class_2_id);
		return list_object_attr_value;
	}

	public void sku_add(T_MALL_SKU sku, List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value) {
		// 插入sku表数据，返回主键
		skuMapper.insert_into_t_mall_sku(sku);
		System.out.println(sku);
		
		// 将sku返回主键放入sku关联对象集合
		for (int i = 0; i < list_sku_attr_value.size(); i++) {
			list_sku_attr_value.get(i).setSku_id(sku.getId());
		}
		
		// 批量插入sku关联对象表数据
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("list_attr_value", list_sku_attr_value);
		skuMapper.insert_into_t_mall_sku_attr_value(map);
	}

}
