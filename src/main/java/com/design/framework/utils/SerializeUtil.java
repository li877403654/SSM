package com.design.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具类
 * 
 * @author John
 * @datatime 2017年12月6日上午10:42:27
 */
public class SerializeUtil {

	// 序列化
	public static byte[] serialize(Object obj) {
		ObjectOutputStream obi = null;
		ByteArrayOutputStream bai = null;
		try {
			bai = new ByteArrayOutputStream();
			obi = new ObjectOutputStream(bai);
			obi.writeObject(obj);
			byte[] byt = bai.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				obi.close();
				bai.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 反序列化
	public static Object unserizlize(byte[] byt) {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		bis = new ByteArrayInputStream(byt);
		try {
			if (byt != null) {
				bis = new ByteArrayInputStream(byt);
				is = new ObjectInputStream(bis);
				obj = is.readObject();
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@SafeVarargs
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] in, Class<T>... classes) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv = is.readObject();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (T) rv;
	}

}
