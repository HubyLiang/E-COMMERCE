package com.liang.manager.dao;

import java.util.HashMap;
import java.util.List;

import com.liang.manager.bean.OBJECT_T_MALL_ATTR;

public interface AttrValueMapper {

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(int class_2_id);


	int insert_into_t_mall_attr(HashMap<Object, Object> map);


	int insert_into_t_mall_value(HashMap<Object, Object> map);


	int insert_into_t_mall_attr(OBJECT_T_MALL_ATTR object_T_MALL_ATTR);

}
