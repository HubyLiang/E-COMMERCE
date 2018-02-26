package com.liang.manager.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MyFileUploadUtil {
	
	public static List<String> imgs_upload(MultipartFile[] files) {

		List<String> image_list = new ArrayList<String>();
		// 图片组上传
		for (int i = 0; i < files.length; i++) {
			try {
				String originalFilename = System.currentTimeMillis() + files[i].getOriginalFilename();
				// 包含图片格式
				files[i].transferTo(new File(
						"D:\\MyWork\\JD\\webstore\\src\\main\\webapp\\upload\\" + originalFilename));// 图片上传
				image_list.add(originalFilename);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image_list;
	}
}
