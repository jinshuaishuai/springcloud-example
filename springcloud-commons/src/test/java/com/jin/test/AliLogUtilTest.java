package com.jin.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.aliyun.openservices.log.exception.LogException;
import com.jin.common.util.AliLogUtil;

public class AliLogUtilTest {
	@Test
	public void testPutLog() {
		Map<String, String> data = new HashMap<>();
		
//		data.put("barCode", "123456");
//		data.put("name", "靳帅");
//		data.put("age", "25");
		
		try {
			AliLogUtil.putLog(data, "篮球运动员", "NBA");
		} catch (LogException e) {
			e.printStackTrace();
		}
	}
}
