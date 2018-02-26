package com.liang.sale.dao;

import java.util.List;
import java.util.Map;

import com.liang.sale.bean.OBJECT_T_MALL_ATTR;
import com.liang.sale.bean.OBJECT_T_MALL_SKU;
import com.liang.sale.bean.T_MALL_SKU;
import com.liang.sale.bean.T_MALL_TRADE_MARK;

public interface SearchMapper {

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2_id_and_attr_value(Map<Object, Object> map);

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(int class_2_id);

	List<T_MALL_TRADE_MARK> select_tm_by_class_2_id(Integer class_2_id);

	OBJECT_T_MALL_SKU select_object_sku_by_sku_id(int sku_id);

	List<T_MALL_SKU> select_sku_list_by_shp_id(int shp_id);

}
