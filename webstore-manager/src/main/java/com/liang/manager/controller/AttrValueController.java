package com.liang.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liang.manager.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.liang.manager.bean.OBJECT_T_MALL_ATTR;
import com.liang.manager.service.AttrValueServiceImpl;

@Controller
public class AttrValueController {

	@Autowired
	AttrValueServiceImpl attrValueServiceImpl;

	@RequestMapping(value = "goto_attr_publish")
	public String goto_sttr_publish() {
		return "manager_attr_publish";
	}

	@RequestMapping(value = "get_attr_by_class_2_id", method = RequestMethod.POST)
	public String get_attr_by_class_2_id(int class_2_id, ModelMap map) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrValueServiceImpl.get_attr_by_class_2_id(class_2_id);
		map.put("list_object_attr_value", list_object_attr_value);

		return "managet_attr_publish_inner";
	}
	
	@ResponseBody
	@RequestMapping(value="get_attr_by_class_2_id_json",method=RequestMethod.POST)
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id_json(int class_2_id){
		
		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrValueServiceImpl.get_attr_by_class_2_id(class_2_id);
		return list_object_attr_value;
	}
	
	@RequestMapping(value="goto_attr_add/{class_2_id}/{class_2_name}")
	public String goto_attr_add(@PathVariable("class_2_id") Integer class_2_id,@PathVariable("class_2_name") String class_2_name){
		return "manager_attr_add";
	}
	
	@RequestMapping(value="attr_add" ,method=RequestMethod.POST)
	public String attr_add(MODEL_OBJECT_T_MALL_ATTR list_attr,int class_2_id){
		
		attrValueServiceImpl.attr_add(list_attr.getList_attr(),class_2_id);
		
		return "manager_attr_add";
	}

}
