package com.jin.common.config;

import com.jin.common.constants.GlobalOssProperties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OssPropertiesConfig {
	
	private Integer oss_source = GlobalOssProperties.OSS_SOURCE;
	
	private String sts_user = GlobalOssProperties.STS_USER;
	
	private Integer source = GlobalOssProperties.SOURCE;
	
	private String auth_path = GlobalOssProperties.AUTH_PATH;
	
	private String oss_region = GlobalOssProperties.OSS_REGION;
	
	private String url = GlobalOssProperties.URL;
	
}
