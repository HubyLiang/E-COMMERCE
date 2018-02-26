package com.liang.user.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class MyConfigUtil {

	public static String getProperties(String key) {
		Properties properties = new Properties();
		InputStream inputStream = MyConfigUtil.class.getClassLoader().getResourceAsStream("myConfigUtil.properties");

		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String property = properties.getProperty(key);
		return property;
	}

}
