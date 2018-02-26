package com.liang.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liang.manager.bean.T_MALL_PRODUCT;
import com.liang.manager.dao.SpuMapper;

@Service
public class SpuServiceImp implements SpuService {

	@Autowired
	SpuMapper spuDao;
	
	public int spu_publish(T_MALL_PRODUCT spu, List<String> image_list) {
		 //插入spu信息,返回spu主键
		spuDao.insert_into_t_mall_product_by_spu(spu);
		
		//批量插入image信息,根据spu主键
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("shp_id", spu.getId());
//		System.out.println(spu.getId());
//		System.out.println("-------------------");
		
		map.put("list", image_list);
		
		int i = spuDao.insert_into_t_mall_product_image(map);
		
		return i;
	}

}
