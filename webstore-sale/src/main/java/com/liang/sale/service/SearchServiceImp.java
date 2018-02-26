package com.liang.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.sale.bean.OBJECT_T_MALL_ATTR;
import com.liang.sale.bean.OBJECT_T_MALL_SKU;
import com.liang.sale.bean.T_MALL_SKU;
import com.liang.sale.bean.T_MALL_SKU_ATTR_VALUE;
import com.liang.sale.bean.T_MALL_TRADE_MARK;
import com.liang.sale.dao.SearchMapper;

@Service
public class SearchServiceImp implements SearchService {

	@Autowired
	private SearchMapper searchMapper;

	/**
	 * 查询检索SKU集合
	 */
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id_and_attr_value(Integer class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value, String order, int tm_id) {

		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("class_2_id", class_2_id);

		if (list_sku_attr_value != null) {

			// 拼接动态sql
			StringBuffer sbf = new StringBuffer();
			sbf.append(" and sku.id in ");
			sbf.append("( select sku_0.sku_id from ");

			for (int i = 0; i < list_sku_attr_value.size(); i++) {
				sbf.append(" (select sku_id from t_mall_sku_attr_value in_attr_val where in_attr_val.shxm_id="
						+ list_sku_attr_value.get(i).getShxm_id() + " and in_attr_val.shxzh_id = "
						+ list_sku_attr_value.get(i).getShxzh_id() + ") sku_" + i);
				if (i < (list_sku_attr_value.size() - 1)) {
					sbf.append(" , ");
				}
			}

			if(list_sku_attr_value.size() > 1){
				sbf.append(" where ");
			}

			for (int j = 0; j < list_sku_attr_value.size(); j++) {
				if (j < (list_sku_attr_value.size() - 1)) {
					sbf.append("sku_" + j + ".sku_id = sku_" + (j + 1) + ".sku_id");
				}

				if (j < (list_sku_attr_value.size() - 2)) {
					sbf.append(" and ");
				}
			}
			sbf.append(" ) ");

			map.put("sql", sbf.toString());
		}
		
		map.put("order", order);
		List<OBJECT_T_MALL_SKU> list_object_sku = searchMapper.select_sku_by_class_2_id_and_attr_value(map);

		return list_object_sku;
	}

	/**
	 * 根据二级分类查找分类属性集合
	 * 
	 * @param class_2_id
	 * @param map
	 * @return
	 */
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(int class_2_id) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = searchMapper.select_attr_by_class_2_id(class_2_id);
		return list_object_attr_value;
	}

	public List<T_MALL_TRADE_MARK> get_tm_by_class_2_id(Integer class_2_id) {

		List<T_MALL_TRADE_MARK> list_tm = searchMapper.select_tm_by_class_2_id(class_2_id);
		return list_tm;
	}

	/**
	 * 根据sku_id 查询sku对象
	 * @param sku_id
	 * @return
	 */
	public OBJECT_T_MALL_SKU get_object_sku_by_sku_id(int sku_id) {
		OBJECT_T_MALL_SKU object_sku = searchMapper.select_object_sku_by_sku_id(sku_id);
		return object_sku;
	}

	/**
	 * 根据商品id查询商品关联的sku的集合
	 * @param shp_id
	 * @return
	 */
	public List<T_MALL_SKU> get_sku_list_by_shp_id(int shp_id) {
		List<T_MALL_SKU> list_sku = searchMapper.select_sku_list_by_shp_id(shp_id);
		return list_sku;
	}

}
