package com.design.framework.utils;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * MD5工具类
 * @author JohnDeng
 * @date 2018年8月8日下午3:39:28
 */
public class Md5Utils {
	
    /* 
     *  使用开发的jar直接应用 
     *  使用外部的jar包中的类：import org.apache.commons.codec.digest.DigestUtils; 
     *  对上面内容的一个封装使用方便 
     */  
    public static String encrypByMd5(String context) {  
        return  DigestUtils.md5Hex(context);
    }  
  
    
    public static void main(String[] args) {
    	System.out.println(encrypByMd5("dong9527"));
	}
}
