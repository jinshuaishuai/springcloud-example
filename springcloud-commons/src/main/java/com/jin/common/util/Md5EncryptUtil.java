package com.jin.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author shuai.jin
 * @date  2020/5/29 9:49
 */
@Slf4j
public final class Md5EncryptUtil {

    private static MessageDigest MD5;
    static {
        try {
            MD5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {

            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }

    private final static char[] cs = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };


    /**
     * md5加密，Base64编码
     * @param targetStr     进行编码的字符串
     * @return              md5加密后，Base64加密后的结果 24长度的秘钥串
     * @throws NoSuchAlgorithmException
     */
    public static String encryptTo24(String targetStr) throws NoSuchAlgorithmException {
        byte[] digest = MD5.digest(targetStr.getBytes());
        String encode = Base64Utils.encodeToString(digest);
        return encode;
    }

    /**
     * md5加密，不经过Base64编码，长度为32位
     * @param       targetStr
     * @return      大写的加密后的字符串
     */
    public static String encryptTo32(String targetStr) {
        try {
            byte[] bs = MD5.digest(targetStr.getBytes("utf-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bs) {
                sb.append(cs[(b >> 4) & 0x0f]);
                sb.append(cs[b & 0x0f]);
            }
            return sb.toString().toLowerCase();
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }

    }

    /**
     * 生成指定长度的随机盐值
     * @param length
     * @return
     */
    public static String generateRandomSaltValue(int length) {
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
}
