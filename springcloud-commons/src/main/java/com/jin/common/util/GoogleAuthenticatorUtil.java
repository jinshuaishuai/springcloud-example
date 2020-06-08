package com.jin.common.util;

import org.apache.commons.codec.binary.Base32;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @date 2019/6/3 9:02
 * @author shuai.jin
 * <p>生成16位长度的秘钥</p>
 * <p>根据用户唯一标识+秘钥生成可以被谷歌认证器识别的特殊字符串</p>
 * <p>校验动态码是否正确</p>
 *
 */
public class GoogleAuthenticatorUtil {

	/**
	 * 最多可偏移的时间
	 */
	static int window_size = 3;


	public static String generateSecret() {
		return generateSecret(16);
	}

	/**
	 *
	 * @param length	指定长度的秘钥串
	 * @return			秘钥
	 */
	public static String generateSecret(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			int val = random.nextInt(32);
			if (val < 26) {
				sb.append((char) ('A' + val));
			} else {
				sb.append((char) ('2' + (val - 26)));
			}
		}
		return sb.toString();
	}

	/**
	 * 根据用户唯一标识+秘钥生成可以被谷歌认证器认证的字符串
	 * @param userKeyId		用户唯一标识
	 * @param secret		秘钥
	 * @return				谷歌认证的字符串
	 */
	public static String getQrBarcode(String userKeyId, String secret) {
		String format = "otpauth://totp/%s?secret=%s";
		return String.format(format, userKeyId, secret);
	}
 

	public static boolean checkCode(String secret, String code, long timeMsec) {
		Base32 codec = new Base32();
		byte[] decodedKey = codec.decode(secret);
		long t = (timeMsec / 1000L) / 30L;
		for (int i = -window_size; i <= window_size; ++i) {
			long hash;
			try {
				hash = verifyCode(decodedKey, t + i);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
				// return false;
			}
			if (hash == Integer.parseInt(code)) {
				return true;
			}
		}
		// The validation code is invalid.
		return false;
	}

	private static int verifyCode(byte[] key, long t) throws Exception {
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}
		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);
		int offset = hash[20 - 1] & 0xF;
		// We're using a long because Java hasn't got unsigned int.
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			truncatedHash |= (hash[offset + i] & 0xFF);
		}
		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;
		return (int) truncatedHash;
	}
}
