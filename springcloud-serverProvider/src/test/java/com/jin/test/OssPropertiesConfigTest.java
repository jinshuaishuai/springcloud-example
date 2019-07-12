package com.jin.test;

import org.junit.Test;

import com.google.gson.Gson;
import com.jin.common.util.HttpClientUtil;
import com.jin.config.OssPropertiesConfig;

public class OssPropertiesConfigTest {
	
	@Test
	public void test() {
		OssPropertiesConfig config = new OssPropertiesConfig();
		System.out.println(config);
	}
	
	@Test
	public void testHttpClient() {
		OssPropertiesConfig oss = new OssPropertiesConfig();
		Gson gson = new Gson();
		String json = gson.toJson(oss);
		String doPostWithJSON = null;
		try {
			doPostWithJSON = HttpClientUtil.doPostWithJSON(oss.getUrl(), json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(doPostWithJSON);
	}
}
