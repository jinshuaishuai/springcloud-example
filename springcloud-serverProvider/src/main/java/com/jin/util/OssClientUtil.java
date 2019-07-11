package com.jin.util;

import com.aliyun.oss.OSSClient;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.jin.config.GlobalOssProperties;
import com.jin.config.OssPropertiesConfig;
import com.jin.config.Rest;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Slf4j
public class OssClientUtil {
	
	public static String endpoint;
	
	public static String accessKeyId;
	
	public static String accessKeySecret;
	
	public static String dirName;
	
	public static String token;
	
	public static String bucketName = GlobalOssProperties.BUCKET;
	
	static {
		OssPropertiesConfig oss = new OssPropertiesConfig();
		Gson gson = new Gson();
		String json = gson.toJson(oss);
		String doPostWithJSON = null;
		try {
			doPostWithJSON = HttpClientUtil.doPostWithJSON(oss.getUrl(), json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Rest rest = gson.fromJson(doPostWithJSON,Rest.class);
		LinkedTreeMap<String, Object> original = (LinkedTreeMap<String, Object>) rest.getOriginal();
		
		LinkedTreeMap<String, Object> data = (LinkedTreeMap<String, Object>) original.get("data");
		
		
		LinkedTreeMap<String, Object> credentials = (LinkedTreeMap<String, Object>) data.get("Credentials");
		
		LinkedTreeMap<String, Object> host = (LinkedTreeMap<String, Object>) data.get("host");
		endpoint = (String) host.get("external");
		accessKeyId = (String) credentials.get("AccessKeyId");
		accessKeySecret = (String) credentials.get("AccessKeySecret");
		token = (String) credentials.get("SecurityToken");
		dirName = (String) data.get("file_name");
	}
	
	public static OSSClient getOssClient() {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, token);
		return ossClient;
	}

}
