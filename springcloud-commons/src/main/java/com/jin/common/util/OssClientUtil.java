package com.jin.common.util;

import com.aliyun.oss.OSSClient;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.jin.common.config.OssPropertiesConfig;
import com.jin.common.config.Rest;
import com.jin.common.constants.GlobalOssProperties;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author 		shuai.jin
 * @datetime	2019.07.11 16:24
 * @description	初始化OSS文件对象连接参数，获取OSSClient对象
 * @see			HttpClientUtil
 * @see			OssPropertiesConfig
 * @see			GlobalOssProperties
 */
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
		//通过STS身份初始化OSS文件对象服务连接参数
		log.info("initing oss params......");
		OssPropertiesConfig oss = new OssPropertiesConfig();
		Gson gson = new Gson();
		String json = gson.toJson(oss);
		String doPostWithJSON = null;
		try {
			//远程服务调用，获取OSS文件对象连接参数
			doPostWithJSON = HttpClientUtil.doPostWithJSON(oss.getUrl(), json);
			log.info("doPostWithJSON value is ---->{}", doPostWithJSON);
		} catch (Exception e) {
			log.error(e.toString());
		}
		
		Rest rest = gson.fromJson(doPostWithJSON,Rest.class);
		LinkedTreeMap<String, Object> original = (LinkedTreeMap<String, Object>) rest.getOriginal();
		log.info("original value is ------>{}", original.toString());
		LinkedTreeMap<String, Object> data = (LinkedTreeMap<String, Object>) original.get("data");
		log.info("data value is ------>{}", data.toString());
		LinkedTreeMap<String, Object> credentials = (LinkedTreeMap<String, Object>) data.get("Credentials");
		log.info("credentials value is ------>{}", credentials.toString());
		LinkedTreeMap<String, Object> host = (LinkedTreeMap<String, Object>) data.get("host");
		log.info("host value is ------>{}", host.toString());
		endpoint = (String) host.get("external");
		accessKeyId = (String) credentials.get("AccessKeyId");
		accessKeySecret = (String) credentials.get("AccessKeySecret");
		token = (String) credentials.get("SecurityToken");
		dirName = (String) data.get("file_name");
		
		log.info("endpoint------>{},accessKeyId------>{},accessKeySecret------>{},token------>{},dirName------>{}", 
				endpoint, accessKeyId, accessKeySecret, token, dirName);
	}
	
	public static OSSClient getOssClient() {
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, token);
		return ossClient;
	}

}
