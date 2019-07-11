package com.jin.test;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

public class FileTest {
	
	@Test
	public void test() {
		File file = new File("/Users/rose/Downloads/");
		System.out.println(file.isFile());
		System.out.println(file.isDirectory());
		System.out.println(file.getPath());
	
	}
	
	@Test
	public void testFileNameUtil() {
		String extension = FilenameUtils.getExtension("http://www/baidu.com/abc.txt");
		
		System.out.println(extension);
	}
	
	@Test
	public void testRandomUUID() {
		String s = UUID.randomUUID().toString();
		System.out.println(s);
	}
}
