package com.liang.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liang.manager.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.liang.manager.bean.OBJECT_T_MALL_ATTR;
import com.liang.manager.bean.T_MALL_PRODUCT;
import com.liang.manager.bean.T_MALL_SKU;
import com.liang.manager.bean.T_MALL_SKU_ATTR_VALUE;
import com.liang.manager.service.SkuService;

@Controller
public class SkuController {

	@Autowired
	SkuService skuServiceImp;

	@RequestMapping("goto_sku_publish")
	public String goto_spu_publish() {
		return "manager_sku_publish";
	}

	@ResponseBody
	@RequestMapping(value = "get_spu_by_class_id_and_tm_id", method = RequestMethod.POST)
	public List<T_MALL_PRODUCT> get_spu_by_class_id_and_tm_id(int class_1_id, int class_2_id, int tm_id) {
		List<T_MALL_PRODUCT> list_spu = skuServiceImp.get_spu_by_class_id_and_tm_id(class_1_id, class_2_id, tm_id);
		return list_spu;
	}

	@RequestMapping(value = "get_attr_by_class_2_id_sku", method = RequestMethod.POST)
	public String get_attr_by_class_2_id_sku(int class_2_id, ModelMap map) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = skuServiceImp.get_attr_by_class_2_id_sku(class_2_id);
		map.put("list_object_attr_value", list_object_attr_value);
		return "managet_sku_publish_inner";
	}

	@RequestMapping(value = "sku_add", method = RequestMethod.POST)
	public ModelAndView sku_add(T_MALL_PRODUCT spu,MODEL_OBJECT_T_MALL_ATTR list_attr,T_MALL_SKU sku) {
		
		List<T_MALL_PRODUCT> list = skuServiceImp.get_spu_by_class_id_and_tm_id(spu.getFlbh1(), spu.getFlbh2(), spu.getPp_id());
		T_MALL_PRODUCT t_MALL_PRODUCT = list.get(0);
		
		//将商品id加入到sku对象中,方便业务层的insert执行
		sku.setShp_id(t_MALL_PRODUCT.getId());
		System.out.println(t_MALL_PRODUCT);
		
		//参数处理,将分类属性集合的对象转换为t_mall_sku_attr_value关联对象
		List<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value = new ArrayList<T_MALL_SKU_ATTR_VALUE>();
		for(int i = 0;i<list_attr.getList_attr().size();i++){
			if(list_attr.getList_attr().get(i).getId() !=0 && list_attr.getList_attr().get(i).getList_value().get(0).getId() !=0){
				T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE = new T_MALL_SKU_ATTR_VALUE();
				t_MALL_SKU_ATTR_VALUE.setShp_id(t_MALL_PRODUCT.getId());
				
				t_MALL_SKU_ATTR_VALUE.setShxm_id(list_attr.getList_attr().get(i).getId());
				t_MALL_SKU_ATTR_VALUE.setShxzh_id(list_attr.getList_attr().get(i).getList_value().get(0).getId());
				list_sku_attr_value.add(t_MALL_SKU_ATTR_VALUE);
			}
		}
		
		// 调用业务层，插入sku和sku关联信息对象
		skuServiceImp.sku_add(sku, list_sku_attr_value);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/goto_sku_publish.do");
		modelAndView.addObject("success", "成功插入一条SKU数据");
		return modelAndView;
	}

}
