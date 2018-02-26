package com.liang.manager.dao;

import java.util.List;
import java.util.Map;

import com.liang.manager.bean.OBJECT_T_MALL_ATTR;
import com.liang.manager.bean.T_MALL_PRODUCT;
import com.liang.manager.bean.T_MALL_SKU;

public interface SkuMapper {

	List<T_MALL_PRODUCT> select_spu_by_class_id_and_tm_id(Map<String, Object> map);

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id_sku(int class_2_id);

	void insert_into_t_mall_sku(T_MALL_SKU sku);

	void insert_into_t_mall_sku_attr_value(Map<Object, Object> map);

}
