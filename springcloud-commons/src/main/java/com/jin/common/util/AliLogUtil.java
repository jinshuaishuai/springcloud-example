package com.jin.common.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import org.springframework.util.Assert;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.request.PutLogsRequest;
import com.jin.common.config.AliLogConfig;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class AliLogUtil {
	
	private static String endpoint = AliLogConfig.ENDPOINT;
	
	private static String accessKeyId = AliLogConfig.ACCESSKEYID;
	
	private static String accessKeySecret = AliLogConfig.ACCESSKEYSECRET;
	
	private static String project = AliLogConfig.PROJECT;
	
	private static String logstore = AliLogConfig.LOGSTORE;
	
	private static Client client = new Client(endpoint, accessKeyId, accessKeySecret);
	
	/**
	 * 传输普通日志数据
	 * @param data			传输的数据
	 * @param topic			日志标题
	 * @param source		日志来源
	 * @throws LogException
	 */
	public static void putLog(Map<String, String> data, String topic, String source) throws LogException {
		Assert.notNull(data, "参数不能为空");
		if(data.size() <= 0) {
			throw new IllegalArgumentException("参数不能为空");
		}
		Vector<LogItem> logGroup = new Vector<LogItem>();
		LogItem logItem = new LogItem();
		for(Entry<String, String> entrySet : data.entrySet()) {
			logItem.PushBack(entrySet.getKey(), entrySet.getValue());
		}
		logGroup.add(logItem);
		PutLogsRequest req = new PutLogsRequest(project, logstore, topic, source, logGroup);
		client.PutLogs(req);
	}
	/**
	 * 传输普通日志数据
	 * @param data			传输的数据
	 * @throws LogException
	 */
	public static void putLog(Map<String, String> data) throws LogException {
		putLog(data, null, null);
	}
	
}
