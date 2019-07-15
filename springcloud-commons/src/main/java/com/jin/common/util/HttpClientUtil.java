package com.jin.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author shuai.jin
 * @datetime	2019.07.11 16:58
 * @description	封装了一些采用HttpClient发送HTTP请求的方法
 *
 */
@Slf4j
public class HttpClientUtil {
    private static final CloseableHttpClient httpClient;
    private static final String CHARSET = "UTF-8";
    private static final String APPLICATION_JSON = "application/json";
    private static final String APPLICATION_JSON_FORM = "application/x-www-form-urlencoded";
 // 每个主机的最大并行链接数,为每个区设置最大的并发连接数,默认每个路由基础上的连接不超过10个
    public static final int DEFAULT_MAX_PER_ROUTE = 10;
    // 客户端总并行链接最大数。默认值总连接数不能超过200
    public static final int MAX_TOTAL = 200;
    // http连接超时时间。默认值设置为60秒
    public static final int CONNECT_TIMEOUT = 600000;
    // socket连接超时时间。默认值设置为60秒
    public static final int SOCKET_TIMEOUT = 600000;
    
    static {

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        cm.setMaxTotal(MAX_TOTAL);

        Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(CONNECT_TIMEOUT);
        builder.setSocketTimeout(SOCKET_TIMEOUT);
        RequestConfig config = builder.build();

        httpClient = HttpClientBuilder.create().setConnectionManager(cm).setDefaultRequestConfig(config).build();
    }

    /**
     * HTTP Get 获取内容,默认编码UTF-8
     * 
     * @param url		请求的url地址
     * @param params	请求的参数
     * @return 			页面内容
     */
    public static String doGet(String url, Map<String, String> params,Map<String, String> headerMap) {
        return doGet(url, params,headerMap, CHARSET);
    }

    /**
     * HTTP Post 获取内容,默认编码UTF-8
     * 
     * @param url		请求的url地址
     * @param params	请求的参数
     * @return 			页面内容
     */
    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, CHARSET);
    }

    /**
     * HTTP Post 获取内容,默认编码UTF-8
     * 
     * @param url		请求的url地址 ?之前的地址
     * @param params	请求的参数
     * @return 页面内容
     */
    public static String doPostFile(String url, Map<String, Object> params) {
        return doPostFile(url, params, CHARSET);
    }

    /**
     * HTTP Get 获取内容
     * 
     * @param url		请求的url地址
     * @param params	请求的参数 例如：将categoryNO=A01,A02 转成 categoryNO=A01&categoryNo=A02
     * @param spiltChar	参数值的分隔符
     * @param flag		是否需要按照分隔符分割
     * @return
     */
    public static String doGet(String url, Map<String, String> params,Map<String, String> headerMap, String spiltChar, boolean flag) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (flag && spiltChar != null) {
                        String[] values = value.split(spiltChar);
                        for (String targetValue : values) {
                            pairs.add(new BasicNameValuePair(entry.getKey(), targetValue));
                        }
                    } else {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            return doGet(url,headerMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * HTTP Get 获取内容
     * 
     * @param url		请求的url地址
     * @param params	请求的参数
     * @param charset 	编码格式
     * @return 页面内容
     */
    public static String doGet(String url, Map<String, String> params,Map<String, String> headerMap, String charset) {
    	if (StringUtils.isBlank(url)) {
            return null;
        }
        
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            return doGet(url,headerMap);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
    public static String doGet(String url,Map<String, String> headerMap) throws RuntimeException,Exception{
    	if (StringUtils.isBlank(url)) {
            return null;
        }
        long start = System.currentTimeMillis();//new Date().getTime();
        
        HttpGet httpGet = new HttpGet(url);
        
        if(headerMap != null && !headerMap.isEmpty()){
        	for (Map.Entry<String,String> entry: headerMap.entrySet()){
        		httpGet.setHeader(entry.getKey(), entry.getValue());
			}
        }
        
        
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            httpGet.abort();
            log.error("HttpClientUtil,error status code:" + statusCode + "|request url:" + url);
            throw new RuntimeException("HttpClientUtil,error status code :" + statusCode);
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null) {
            result = EntityUtils.toString(entity, CHARSET);
            long end = System.currentTimeMillis();//new Date().getTime();
            log.info("HttpClientUtil,request url:" + url + "|time:" + (end - start) + "ms");
            log.debug("HttpClientUtil,response result :" + result);
        }
        EntityUtils.consume(entity);
        response.close();
        return result;

    }



    /**
     * HTTP Post 获取内容
     * 
     * @param url
     *            请求的url地址 ?之前的地址
     * @param params
     *            请求的参数
     * @param charset
     *            编码格式
     * @return 页面内容
     */
    public static String doPost(String url, Map<String, String> params, String charset) {
    	if (StringUtils.isBlank(url)) {
            return null;
        }
        // debug打印日志
        log.debug("HttpClientUtil,request url:" + url + " and params:" + params.toString());
        try {
            long start = System.currentTimeMillis();//new Date().getTime();
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                log.error("HttpClient,error status code:" + statusCode + "|request url:" + url + "|params:" + params.toString());
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
                long end = new Date().getTime();
                log.info("HttpClientUtil,request url:" + url + "|params:" + params.toString() + "|time:" + (end - start) + "ms");
                log.debug("HttpClientUtil,response result :" + result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            log.error("HttpClientUtil,error request url:" + url + "|params:" + params.toString());
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * HTTP Post 获取内容
     * 
     * @param url
     *            请求的url地址 ?之前的地址
     * @param params
     *            请求的参数
     * @param headerMap
     *  		请求头参数
     * @param charset
     *            编码格式
     * @return 页面内容
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> headerMap,String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // debug打印日志
        log.debug("HttpClientUtil,request url:" + url + " and params:" + params.toString());
        try {
            long start = System.currentTimeMillis();//new Date().getTime();
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            
            HttpPost httpPost = new HttpPost(url);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            
            if(headerMap != null && !headerMap.isEmpty()){
            	for (Map.Entry<String,String> entry: headerMap.entrySet()){
            		httpPost.setHeader(entry.getKey(), entry.getValue());
				}
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                log.error("HttpClient,error status code:" + statusCode + "|request url:" + url + "|params:" + params.toString());
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
                long end = new Date().getTime();
                log.info("HttpClientUtil,request url:" + url + "|params:" + params.toString() + "|time:" + (end - start) + "ms");
                log.debug("HttpClientUtil,response result :" + result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            log.error("HttpClientUtil,error request url:" + url + "|params:" + params.toString());
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * HTTP Post 获取内容
     * 
     * @param url
     *            请求的url地址 ?之前的地址
     * @param params
     *            请求的参数
     * @param charset
     *            编码格式
     * @return 页面内容
     */
    public static String doPost(String url, Map<String, String> params, String charset,String referer) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // debug打印日志
        log.debug("HttpClientUtil,request url:" + url + " and params:" + params.toString());
        try {
            long start = System.currentTimeMillis();//new Date().getTime();
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Referer", referer);
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                log.error("HttpClient,error status code:" + statusCode + "|request url:" + url + "|params:" + params.toString());
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
                long end = new Date().getTime();
                log.info("HttpClientUtil,request url:" + url + "|params:" + params.toString() + "|time:" + (end - start) + "ms");
                log.debug("HttpClientUtil,response result :" + result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            log.error("HttpClientUtil,error request url:" + url + "|params:" + params.toString());
            log.error(e.getMessage(), e);
        }
        return null;
    }
  

    /**
     * 用于带文件的表单提交,也可用于普通表单提交，可多文件上传
     * 
     * @param url
     *            上传接口的url
     * @param params
     * @return String
     */
    public static String doPostFile(String url, Map<String, Object> params, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // debug打印日志
        log.debug("HttpClientUtil,request url:" + url + " and params:" + params.toString());

        try {
            long start = System.currentTimeMillis();//new Date().getTime();
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = params.get(key);
                if (value instanceof File) {
                    entityBuilder.addPart(key, new FileBody((File) value));
                } else if (value instanceof InputStream) {
                    entityBuilder.addBinaryBody(key, (InputStream) value);
                } else {
                    entityBuilder.addPart(key, new StringBody((String) value, ContentType.create("text/plain", charset)));
                }
            }

            HttpPost httpPost = new HttpPost(url);
            HttpEntity fileEntity = entityBuilder.build();
            httpPost.setEntity(fileEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                log.error("HttpClient,error status code:" + statusCode + "|request url:" + url + "|params:" + params.toString());
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
                long end = new Date().getTime();
                log.info("HttpClientUtil,request url:" + url + "|params:" + params.toString() + "|time:" + (end - start) + "ms");
                log.debug("HttpClientUtil,response result :" + result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            log.error("HttpClientUtil,error request url:" + url + "|params:" + params.toString());
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
    /**
     * http post json 字符串请求
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String doPostWithJSON(String url, String json) throws Exception {
    	return doPostWithJSON(url, json, CHARSET);
    }
    
    public static String doPostWithJSON(String url, String json, String charset) throws Exception {
        
    	if (StringUtils.isBlank(url)) {
            return null;
        }
    	
        log.debug("HttpClientUtil,request url:" + url + " and params:" + json);
    	try{
//    		long start = System.currentTimeMillis();//new Date().getTime();
    		HttpPost httpPost = new HttpPost(url);
    		StringEntity stringEntity = new StringEntity(json, charset);
    		stringEntity.setContentType(APPLICATION_JSON);
    		stringEntity.setContentEncoding(charset);
    		httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                log.error("HttpClient,error status code:" + statusCode + "|request url:" + url + "|json:" + json);
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
//                long end = System.currentTimeMillis();//new Date().getTime();
                //log.info("HttpClientUtil,request url:" + url + "|json:" + json + "|time:" + (end - start) + "ms");
                log.debug("HttpClientUtil,response result :" + result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
    	}catch(Exception e){
    		 log.error("HttpClientUtil,error request url:" + url + "|json:" + json);
             log.error(e.getMessage(), e);
    	}
    	return null;
    }
    /**
     * http post json 字符串请求
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String doPostWithJSON(String url, String json,int timeOut) throws Exception {
    	return doPostWithJSON(url, json, CHARSET,timeOut);
    }
    public static String doPostWithJSON(String url, String json, String charset,int timeOut) throws Exception {
        
    	if (StringUtils.isBlank(url)) {
            return null;
        }
    	long start = 0;
    	long end = 0 ;
        log.debug("HttpClientUtil,request url:" + url + " and params:" + json);
    	try{
    		start = System.currentTimeMillis();//new Date().getTime();
    		HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(timeOut)
                    .setConnectTimeout(timeOut)
                    .setSocketTimeout(timeOut)
                    .setExpectContinueEnabled(false)
                    .build();
            httpPost.setConfig(requestConfig);
        	StringEntity stringEntity = new StringEntity(json, charset);
    		stringEntity.setContentType(APPLICATION_JSON);
    		stringEntity.setContentEncoding(charset);
    		httpPost.setEntity(stringEntity);
    		
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                log.error("HttpClient,error status code:" + statusCode + "|request url:" + url + "|json:" + json);
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
                end = System.currentTimeMillis();//new Date().getTime();
                log.info("HttpClientUtil,request url:" + url + "|json:" + json + "|time:" + (end - start) + "ms");
                log.debug("HttpClientUtil,response result :" + result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
    	}catch(ConnectTimeoutException e){
    		end = System.currentTimeMillis();//new Date().getTime();
    		log.error("HttpClientUtil,error timeout request url:" + url + "|json:" + json+ "|time:" + (end - start) + "ms");
    		log.error(e.getMessage(), e);
    	}catch(SocketTimeoutException e){
    		end = System.currentTimeMillis();//new Date().getTime();
   		 	log.error("HttpClientUtil,error timeout request url:" + url + "|json:" + json+ "|time:" + (end - start) + "ms");
   		 	log.error(e.getMessage(), e);
    	}catch(Exception e){
    		 log.error("HttpClientUtil,error request url:" + url + "|json:" + json);
             log.error(e.getMessage(), e);
    	}
    	return null;
    }   
    /**
     * http post json 字符串请求
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String doPostWithJSONFORM(String url, String json) throws Exception {
    	return doPostWithJSONFORM(url, json, CHARSET);
    }
    
    public static String doPostWithJSONFORM(String url, String json, String charset) throws Exception {
        
    	if (StringUtils.isBlank(url)) {
            return null;
        }
    	
        log.debug("HttpClientUtil,request url:" + url + " and params:" + json);
    	try{
    		long start = System.currentTimeMillis();//new Date().getTime();
    		HttpPost httpPost = new HttpPost(url);
    		StringEntity stringEntity = new StringEntity(json, charset);
    		stringEntity.setContentType(APPLICATION_JSON_FORM);
    		stringEntity.setContentEncoding(charset);
    		httpPost.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                log.error("HttpClient,error status code:" + statusCode + "|request url:" + url + "|json:" + json);
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, CHARSET);
                long end = System.currentTimeMillis();//new Date().getTime();
                log.info("HttpClientUtil,request url:" + url + "|json:" + json + "|time:" + (end - start) + "ms");
                log.debug("HttpClientUtil,response result :" + result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
    	}catch(Exception e){
    		 log.error("HttpClientUtil,error request url:" + url + "|json:" + json);
             log.error(e.getMessage(), e);
    	}
    	return null;
    }

    /**
     * 下载文件
     * 
     * @param url
     *            http://www.xxx.com/img/111.jpg
     * @param destFileName
     *            xxx.jpg/xxx.png/xxx.txt
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void downloadFile(String url, String destFileName) throws ClientProtocolException, IOException {
        if (StringUtils.isBlank(url)) {
            return;
        }
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        File file = new File(destFileName);
        try {
            FileOutputStream fout = new FileOutputStream(file);
            int len = -1;
            byte[] tmp = new byte[1024];
            while ((len = in.read(tmp)) != -1) {
                fout.write(tmp, 0, len);
            }
            fout.flush();
            fout.close();
        } finally {
            // 关闭低层流。
            in.close();
        }
        httpClient.close();
    }
}
