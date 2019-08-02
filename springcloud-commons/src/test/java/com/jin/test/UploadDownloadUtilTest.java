package com.jin.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.jin.common.util.UploadDownloadUtil;
import com.jin.entity.pojo.ImageInfo;

public class UploadDownloadUtilTest {

	@Test
	public void testDownloadFile() {
		try {
			String local = UploadDownloadUtil.downloadFileFromNet2Local("http://api-img.dislux.com/img/upload/img/store/dad2c5abdebe40fd926f643cf95330d0/1529561781154.jpg", "/Users/rose/Downloads");
			System.out.println(local);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUploadFile() {
		File file = new File("/Users/rose/Downloads/6cbc9a21-1c34-40d8-b774-7276f6f9bb5d.jpg");
		ImageInfo imageInfo = UploadDownloadUtil.uploadLocalFile2OSS(file);
		System.out.println(imageInfo);
	}
}
