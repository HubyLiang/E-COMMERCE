package com.liang.sale.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class MyWsFactory {

	public static <T> T getWsService(String url, Class<T> t){
		
		JaxWsProxyFactoryBean jpb = new JaxWsProxyFactoryBean();
		
		jpb.setAddress(url);
		
		T create = jpb.create(t);
		
		return create;
		
	}
}
