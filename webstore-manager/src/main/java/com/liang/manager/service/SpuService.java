package com.liang.manager.service;

import java.util.List;

import com.liang.manager.bean.T_MALL_PRODUCT;

public interface SpuService {
	public int spu_publish(T_MALL_PRODUCT spu, List<String> image_list);
}
