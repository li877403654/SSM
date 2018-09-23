package com.design.framework.utils;

import java.util.Date;
import java.util.UUID;
/**
 * token工具类
 * @author JohnDeng
 * 2017年11月10日下午5:40:35
 */
public class TokenUtils {

	
	private static final String KEY="tongdun"; //盐,暂时写死，后期通过xml读取
	
	/**
	 * 获取token
	 * UUID+盐经过MD5加密
	 * @author JohnDeng
	 * 2017年11月10日下午5:40:48
	 * @return
	 */
	public static String getToken(){
		
		return Md5Utils.encrypByMd5(UUID.randomUUID()+KEY+DateUtil.getMillis(new Date()));
	}
	
	public static void main(String[] args) {
		System.out.println(getToken());
	}
}
