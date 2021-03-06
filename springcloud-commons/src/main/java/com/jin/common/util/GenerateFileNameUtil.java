package com.jin.common.util;

import java.io.File;
import java.util.Random;
/**
 * 
 * @author shuai.jin
 * @datetime	2019.07.11 16:26
 * @description	随机生成文件名工具类，保证每个文件名唯一
 *
 */
public class GenerateFileNameUtil {
	/*
	 * 私有化构造方法
	 */
	private GenerateFileNameUtil() {
		
	}
	private static GenerateFileNameUtil instance = new GenerateFileNameUtil();
	public static GenerateFileNameUtil getInstance() {
		return instance;
	}
	
	/**
	 * 生成随机文件名
	 * @param 	file
	 * @return	如果文件不存在或不是一个文件返回null
	 */
	public static String generateFileName(File file) {
		return generateFileName(file,"");
	}
	
	/**
	 * 生成随机的文件名
	 * @param file		本地File对象
	 * @param prefix	特定前缀的文件名
	 * @return			如果文件不存在或不是一个文件返回null
	 */
	public static String generateFileName(File file, String prefix) {
		if(file.isFile()) {
			String path = file.getPath();
			int index = path.indexOf(".");
			String fileSuffix = path.substring(index);
			String randomFileName = prefix + System.currentTimeMillis() + new Random().nextInt(1000);
			String fileName = randomFileName + fileSuffix;
			return fileName;
		}
		return null;
	}
}
