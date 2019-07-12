package com.jin.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.jin.common.util.UploadDownloadUtil;

public class UploadDownloadUtilTest {

	@Test
	public void testDownloadFile() {
		try {
			String local = UploadDownloadUtil.downloadFileFromNet2Local("https://i.ibb.co/3pxzq6N/abc.jpg", "/Users/rose/Downloads");
			System.out.println(local);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUploadFile() {
		File file = new File("/Users/rose/Downloads/e0955491-a280-4105-a30a-5ce421dd3911.jpg");
		String localFile = UploadDownloadUtil.uploadLocalFile2OSS(file);
		System.out.println(localFile);
	}
}
