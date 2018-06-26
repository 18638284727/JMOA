package com.jm.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.shiro.codec.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Component;

/**
 * 序列化封装
 */
@Component
public class SerializableUtiles {

	@SuppressWarnings("resource")
	public static String serialize(Object object) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(object);
			return Base64.encodeToString(outputStream.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("serialize session error:" + e.getMessage());
		}
	}
	
	public static Object deSerialize(String sessionId){
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64.decode(sessionId));
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			return (Object) ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException("deserialize string error:" + e.getMessage());
		}
	}
	
}
