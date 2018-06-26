package com.jm.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * 工具类：密码加密
 */
@Component
public class CipherEncryption {

	/**
	 * MD5加密
	 * @param credentials 密码
	 * @param salt 盐值
	 * @return 加密后的字符串
	 */
	public static Object MD5(Object credentials, Object salt)
	{
		Object bytes = ByteSource.Util.bytes(salt);
		Object result = new SimpleHash("MD5", credentials, bytes, 1024);
		return result;
	}
	
	/**
	 * SHA1加密
	 * @param credentials 密码
	 * @param salt 盐值
	 * @return 加密后的字符串
	 */
	public static Object SHA1(Object credentials, Object salt)
	{
		Object bytes = ByteSource.Util.bytes(salt);
		Object result = new SimpleHash("SHA1", credentials, bytes, 1024);
		return result;
	}
}
