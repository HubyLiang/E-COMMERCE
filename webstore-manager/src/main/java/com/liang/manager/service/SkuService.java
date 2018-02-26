package com.liang.manager.service;

import java.util.List;

import com.liang.manager.bean.OBJECT_T_MALL_ATTR;
import com.liang.manager.bean.T_MALL_PRODUCT;
import com.liang.manager.bean.T_MALL_SKU;
import com.liang.manager.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuService {

	List<T_MALL_PRODUCT> get_spu_by_class_id_and_tm_id(int class_1_id, int class_2_id, int tm_id);

	List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id_sku(int class_2_id);

	void sku_add(T_MALL_SKU sku, List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value);

}
