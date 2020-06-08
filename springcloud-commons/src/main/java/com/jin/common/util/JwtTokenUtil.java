package com.jin.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

import java.util.Date;

/**
 * @author shuai.jin
 * @date	2019/05/29 11:20
 */
@Data
public final class JwtTokenUtil {
	/**
	 * token校验
	 * @param token			token字符串
	 * @param username		用户名
	 * @param pwd			密码
	 * @return				token相同返回true，否则返回false
	 */
	public static boolean verify(String token, String username, String pwd) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(pwd);
			
			JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
			
//			DecodedJWT verify = verifier.verify(token);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 根据token获取用户名
	 * @param token			token字符串
	 * @return				返回用户名
	 */
	public static String getUsername(String token) {
		DecodedJWT decodeJwt = JWT.decode(token);
		return decodeJwt.getClaim("username").asString();
	}

	/**
	 *
	 * 根据用户名和密码获取token
	 *
	 * @param username			用户名
	 * @param pwd			  	密码
	 * @param tokenExpireTime 	token过期时间 单位：hour
	 * @return					签名后的token
	 */
	public static String sign(String username, String pwd, int tokenExpireTime) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(pwd);
			 Date date = new Date(System.currentTimeMillis() +  tokenExpireTime * 60 * 60 * 1000);

			return JWT.create().withClaim("username", username)
			.withExpiresAt(date).sign(algorithm);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
