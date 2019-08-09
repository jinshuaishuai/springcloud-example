package com.jin.common.util;

/**
 * 生成用户编号工具类
 * @author shuai.jin
 *
 */
public class GenerateUserNoUtil {
	/*
	 * 工具类：私有化构造方法
	 */
	private GenerateUserNoUtil() {
	}
	/**
	 * 该方法不能保证每秒并发量在1000以上，产生唯一id（原因，位数太短），在并发量较低情况下，可以较大程度保证生成的id唯一，
	 * 当len长度越长，生成的id相对越唯一
	 * 默认生成10位时间戳加随机数的唯一id
	 * @param prefix	前缀	例如AU
	 * @return			返回十二位字母加数字	AU1238088933
	 */
	public final static String generateUserNo(String prefix) {
		return generateUserNo(10,prefix);					//兼容以前接口，保证以前接口不动
	}
	/**
	 * 
	 * @return	默认生成10位随机数字
	 */
	public final static String generateUserNo() {
		return generateUserNo(10,null);
	}
	
	/**
	 * 生成指定位数随机数
	 * @param len			len长度位的随机数
	 * @param prefix		前缀
	 * @return
	 */
	public static String generateUserNo(int len,String prefix) {
		if(prefix == null) {
			prefix = "";
		}
		if(len <= 7) {
			throw new RuntimeException("位数不能低于7");
		} else {
			int i = len -7;
			long k = (long)Math.pow(10, i - 1) * 9;		
			int j = (int)Math.pow(10, i -1);
			String sub = String.valueOf(System.currentTimeMillis()).substring(0,11);	//取毫秒的前11位，转换为36进制，长度位7
			int random = (int)(Math.random() * k) + j;	//举例：len为10，此时取[100-999]之间的三位随机数，为9，取[10,99]的两位随机数
			Long s = new Long(sub);
			String ss = Long.toString(s, 36);
			return prefix + ss + random;
		}
	}
}
