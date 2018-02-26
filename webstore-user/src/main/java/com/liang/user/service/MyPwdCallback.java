package com.liang.user.service;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import com.liang.user.util.MyConfigUtil;


public class MyPwdCallback implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		WSPasswordCallback wsp = (WSPasswordCallback) callbacks[0];
		
		String identifier = wsp.getIdentifier();
		
		String properties = MyConfigUtil.getProperties(identifier);
		
		wsp.setPassword(properties);
		
	}

}
