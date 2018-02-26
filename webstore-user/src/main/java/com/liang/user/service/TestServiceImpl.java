package com.liang.user.service;

import org.springframework.stereotype.Service;

import com.liang.user.bean.T_MALL_USER;

@Service
public class TestServiceImpl implements TestService{

	@Override
	public String ping() {
		System.out.println("ping ...");
		return "pong";
	}

}
