package com.jin.test;

import com.jin.common.util.RSAUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author shuai.jin
 * @description TODO
 * @Date 2020/5/21 10:55
 */
public class RSAUtilTest {
    @Test
    public void testRSA() throws Exception {

       /* MessageDigest md5 = MessageDigest.getInstance(md5);
        byte[] digest = md5.digest(admin.getBytes());
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(digest);
        System.out.println(encode);*/

        KeyPair keyPair = RSAUtil.getKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        String decrypt = RSAUtil.decrypt("LRG4J9dogboAliI6yWg52gh5w2qOKyTjMKi9cvmvtu5t6OHX6rZCJIyDgFimZCu2v4OKFScCYosH/1CHcV7dAFq34UTeOakAkbKELcw3C/1Zul/JJ8ZIFq0L80i2UAI46TN1yOU/ijf7aQK88vulmRjvHRKBoSi4WaZaK3t3rZIt5vl9r72XjNFbIKPxSYfdcrvqbKztzMwQrrtr5Vu0SnIJGpmliU9nPep0F8sANSX5tWNcn2vRgbjnO1l1khPBbb9zCnXKg4T4u5/xJnTEGuqvbXPphgq1sYl9hwcsW8cQUC9KrW3jNH2jfswYQhPxEMBJAfqUs/sY+F8xYgdOcw==", aPrivate);
        System.out.println(decrypt);
    }

    /**
     * 测试签名
     * @throws Exception
     */
    @Test
    public void testSign() throws Exception {
        String sign = RSAUtil.sign("root");
        System.out.println(sign);
        boolean ve = RSAUtil.verify("root", sign);
        System.out.println(ve);

    }

    /*
        测试加密解密
     */
    @Test
    public void testNewRSA() throws Exception {
        String encrypt = RSAUtil.encrypt("good");
        System.out.println(encrypt);
        String decrypt = RSAUtil.decrypt(encrypt);
        System.out.println(decrypt);
    }
    /*
        读取配置文件的方式获取公钥和秘钥
     */
    @Test
    public void testFileRead() throws Exception {

        StringBuffer stringBuffer = new StringBuffer();
        String str = "";

        File file = new File("src\\main\\resources\\config\\public-key.pem");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while((str = bufferedReader.readLine()) != null) {
            stringBuffer.append(str + "\n");
        }
        System.out.println(stringBuffer.toString());
        PublicKey publicKey = RSAUtil.getPublicKey(stringBuffer.toString());
        String encrypt = RSAUtil.encrypt("root", publicKey);
        System.out.println("加密后的结果为：" + encrypt);

        file = new File("src\\main\\resources\\config\\private-key.pem");
        bufferedReader = new BufferedReader(new FileReader(file));
        str = "";
        stringBuffer = new StringBuffer();
        while((str = bufferedReader.readLine()) != null) {
            stringBuffer.append(str + "\n");
        }
        System.out.println("私钥的结果为：" + stringBuffer.toString());
        String privateKeyStr = stringBuffer.toString();
        PrivateKey privateKey = RSAUtil.getPrivateKey(privateKeyStr);
        String decrypt = RSAUtil.decrypt(encrypt, privateKey);
        System.out.println(decrypt);
    }

}
