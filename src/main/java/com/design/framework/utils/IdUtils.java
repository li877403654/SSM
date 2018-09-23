package com.design.framework.utils;

import java.util.UUID;
/**
 * ID工具类
 * @author JohnDeng
 * @datatime 2018年5月2日下午5:17:00
 */
public class IdUtils {

	public static String getId(){
		return UUID.randomUUID().toString();
	}
}
