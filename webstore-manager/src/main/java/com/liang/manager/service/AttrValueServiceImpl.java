package com.liang.manager.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.manager.bean.OBJECT_T_MALL_ATTR;
import com.liang.manager.dao.AttrValueMapper;

@Service
public class AttrValueServiceImpl implements AttrValueService {

	@Autowired
	private AttrValueMapper attrValueMapper;

	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(int class_2_id) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrValueMapper.select_attr_by_class_2_id(class_2_id);
		return list_object_attr_value;
	}

	public int attr_add(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {

		int num = 0;
		for (int i = 0; i < list_attr.size(); i++) {
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			
			list_attr.get(i).setFlbh2(class_2_id);
			
			//插入一条分类属性,使用map或 mybatis@Param注解双参数,主键返回给t_mall_attr时id封装不上
//			int a = attrValueMapper.insert_into_t_mall_attr(map);			
//			map.put("attr", list_attr.get(i));
			int a = attrValueMapper.insert_into_t_mall_attr(list_attr.get(i));
			
			map.put("list_value", list_attr.get(i).getList_value());
			map.put("attr_id",list_attr.get(i).getId());
			
			//批量插入分类属性值集合
			int b = attrValueMapper.insert_into_t_mall_value(map);
			num++;
		}
		return num;
	}

}
