package com.jin.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import com.aliyun.oss.OSSClient;
import com.jin.entity.pojo.ImageInfo;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
/**
 * 
 * @author		shuai.jin
 * @datetime	2019.07.11
 * @description	封装文件上传下载工具类，隐藏OSS连接操作
 * @see			OssClientUtil
 * @see			OSSClient
 * @see			FilenameUtils
 *
 */
@Slf4j
public class UploadDownloadUtil {
	/**
	 * @param file	上传本地图片
	 * @return		上传成功返回一个oss服务器图片保存路径，否则返回null
	 */
	public static ImageInfo uploadLocalFile2OSS(File file) {
		if(file.isFile()) {
			OSSClient ossClient = OssClientUtil.getOssClient();
			String fileName = GenerateFileNameUtil.generateFileName(file, OssClientUtil.bucketName);
			String key = OssClientUtil.dirName + fileName;
			try {
				ossClient.putObject(OssClientUtil.bucketName, key, file);
				String replace = OssClientUtil.endpoint.replace("https://oss-cn", "https://mstore.oss-cn");
				String url = replace + "/" + key;
				ossClient.shutdown();
				//文件成功上传到阿里OSS服务器后，删除本地文件
				file.delete();
				String doGet = HttpClientUtil.doGet(url + "@info", null);
				//返回上传到阿里OSS服务器的图片信息
				JSONObject fromObject = JSONObject.fromObject(doGet);
				ImageInfo imageInfo = (ImageInfo) JSONObject.toBean(fromObject,ImageInfo.class);
				imageInfo.setUrl(url);
				log.info("imageInfo --->{}", imageInfo);
				return imageInfo;
			} catch(Exception e) {
				//文件上传到OSS服务器出现错误
				log.error(e.toString());
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param fileUrl		网络文件的URL
	 * @param localPath		保存到本地磁盘上的路径
	 * @return				返回文件保存的本地路径+文件名
	 * @throws IOException
	 */
	public	static String downloadFileFromNet2Local(String fileUrl,String localPath) throws IOException {
		
		//获取网络文件后缀名
		String extension = FilenameUtils.getExtension(fileUrl);
		String fileName = UUID.randomUUID().toString() + "." + extension;
		URL url = new URL(fileUrl);
		URLConnection conn = url.openConnection();
		//设置连接超时时间
		conn.setConnectTimeout(5000);
		//获取网路文件流
		InputStream is = conn.getInputStream();
		//设置1024的字节数组
		byte[] arr = new byte[1024];
		int len = 0;
		File file = new File(localPath);
		//此时传入的是一个文件夹，如果不存在，则新建
		if(!file.exists()) {
			file.mkdirs();
		}
		String fullPath = file.getPath() + "/" + fileName;
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(fullPath));
		while((len = is.read(arr)) != -1) {
			os.write(arr, 0, len);
		}
		is.close();
		os.close();
		//返回网络文件下载到本地的全路径
		return fullPath;
	}
}
