package com.liang.sale.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.liang.user.service.MyPwdCallbackClient;

public class MyWsFactoryBean<T> implements FactoryBean<T> {

	private Class<T> type;

	private String url;

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public T getObject() throws Exception {
		JaxWsProxyFactoryBean jpb = new JaxWsProxyFactoryBean();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		map.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		map.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyPwdCallbackClient.class.getName());
		map.put(WSHandlerConstants.USER, "username");

		WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(map);

		jpb.getOutInterceptors().add(wss4jOutInterceptor);

		jpb.setAddress(url);

		T create = jpb.create(type); // 必须在拦截器之后创建
		return create;
	}

	@Override
	public Class<?> getObjectType() {
		return this.type;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
