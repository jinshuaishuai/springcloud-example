package com.jin.common.util;

import com.jin.common.constants.RSAPublicKeyPrivateKeyConstant;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author shuai.jin
 * @description 支持明文的加密与解密，数据签名，验签操作
 * @Date 2020/5/21 10:44
 */
public class RSAUtil {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 246;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        //设置RSA秘钥长度
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
      * @param privateKey
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param encryptData 待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String encryptData, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = encryptData.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(encryptData.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptData.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String pwd, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(pwd);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param signData 待签名数据
     * @param privateKey 私钥
     * @return 返回签名后的字符串
     */
    public static String sign(String signData, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(signData.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

    /**
     * 封装RSA加密算法，采用系统提供的公钥进行加密
     * @param encryptData
     * @return  加密后的密码
     * @throws Exception
     */
    public static String encrypt(String encryptData) throws Exception {
        PublicKey publicKey = getPublicKey(RSAPublicKeyPrivateKeyConstant.PUBLICKEY);
        String encrypt = encrypt(encryptData, publicKey);
        return encrypt;
    }

    /**
     * 封装RSA解密算法，采用系统提供的秘钥进行解密
     * @param pwd   要解密的密码
     * @return      解密后的明文
     * @throws Exception
     */
    public static String decrypt(String pwd) throws Exception {
        PrivateKey privateKey = getPrivateKey(RSAPublicKeyPrivateKeyConstant.PRIVATEKEY);
        String decrypt = decrypt(pwd, privateKey);
        return decrypt;
    }

    public static String sign(String signData) throws Exception {
        PrivateKey privateKey = getPrivateKey(RSAPublicKeyPrivateKeyConstant.PRIVATEKEY);
        String sign = sign(signData, privateKey);
        return sign;
    }
    public static boolean verify(String srcData, String sign) throws Exception {
        PublicKey publicKey = getPublicKey(RSAPublicKeyPrivateKeyConstant.PUBLICKEY);
        boolean verify = verify(srcData, publicKey, sign);
        return verify;
    }

    public static void main(String[] args) throws Exception {

        /*
            获取公钥，秘钥对
         */
        KeyPair keyPair = getKeyPair();
        PublicKey aPublic = keyPair.getPublic();
        //采用公钥加密
        String pwd = encrypt("root", aPublic);
        System.out.println(pwd);
        //秘钥解密
        String decrypt = decrypt(pwd, keyPair.getPrivate());
        System.out.println(decrypt);

        //采用秘钥进行签名
        String sign = sign("good", keyPair.getPrivate());
        System.out.println(sign);

        //采用公钥进行验签
        boolean ve = verify("呵呵", keyPair.getPublic(), sign);
        System.out.println(ve);
    }
}
