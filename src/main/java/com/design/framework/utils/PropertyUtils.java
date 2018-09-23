package com.design.framework.utils;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
/**
 * 属性工具类
 * @author JohnDeng
 * @datatime 2018年5月2日下午5:23:42
 */
public class PropertyUtils {
	
	/**
	 * 根据路径获取配置
	 * @author JohnDeng
	 * @datatime 2018年7月6日下午2:50:08
	 * @param name
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String getStringPropertiesByFiltePath(String name,String path) throws Exception{
		Properties prop = new Properties();  
        InputStream in = PropertyUtils.class.getResourceAsStream(path);
        prop.load(in);     ///加载属性列表
        Iterator<String> it=prop.stringPropertyNames().iterator();
        while(it.hasNext()){
            String key=it.next();
            if(key.equals(name)){
            	return prop.getProperty(key);
            }
        }
        in.close();
		return null;
	}
	
	
	public static int getIntPropertiesByFiltePath(String name,String path) throws Exception{
		
		return Integer.parseInt(getStringPropertiesByFiltePath(name, path));
	}
	
	
	public static boolean getBooleanPropertiesByFiltePath(String name,String path) throws Exception{
		
		return Boolean.getBoolean(getStringPropertiesByFiltePath(name, path));
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(getStringPropertiesByFiltePath("username","config/jdbc.properties"));
	}
	
}
