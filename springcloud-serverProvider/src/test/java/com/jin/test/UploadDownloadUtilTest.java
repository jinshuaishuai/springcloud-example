package com.jin.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.jin.util.UploadDownloadUtil;

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
		File file = new File("/Users/rose/Downloads/194d6e81-5f29-4ec5-8c4f-dc1712116c69.jpg");
		String localFile = UploadDownloadUtil.uploadLocalFile(file);
		System.out.println(localFile);
	}
}
