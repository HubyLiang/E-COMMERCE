package com.liang.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertiesTest {
	
	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = MyPropertiesTest.class.getClassLoader().getResourceAsStream("dbConfig.properties");
		properties.load(inputStream);
		String path = properties.getProperty("imgPath");
		System.out.println(path);
	}

}
