package com.liang.sale.service;

import java.util.List;

import com.liang.sale.bean.OBJECT_T_MALL_ATTR;
import com.liang.sale.bean.OBJECT_T_MALL_SKU;
import com.liang.sale.bean.T_MALL_SKU_ATTR_VALUE;
import com.liang.sale.bean.T_MALL_TRADE_MARK;

public interface SearchService {
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id_and_attr_value(Integer class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value, String string, int i);
	
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(int class_2_id);
	
	public List<T_MALL_TRADE_MARK> get_tm_by_class_2_id(Integer class_2_id);

}
