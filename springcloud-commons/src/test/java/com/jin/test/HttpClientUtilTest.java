package com.jin.test;

import org.junit.Test;

import com.jin.common.util.HttpClientUtil;
import com.jin.entity.pojo.ImageInfo;

import net.sf.json.JSONObject;

public class HttpClientUtilTest {
	@Test
	public void test1() throws RuntimeException, Exception {
		String doGet = HttpClientUtil.doGet("https://mstore.oss-cn-beijing.aliyuncs.com/supperlier/testing/public/2019/17135d2c35624e06d/mstore1563178338804284.jpg@info", null);
		JSONObject fromObject = JSONObject.fromObject(doGet);
		ImageInfo bean = (ImageInfo) JSONObject.toBean(fromObject, ImageInfo.class);
		System.out.println(bean);
		
		
	}
}
