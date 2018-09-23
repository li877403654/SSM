package com.design.framework.utils;

/**
 * 获取内存工具类
 * @author JohnDeng
 * @date 2018年8月8日下午3:39:40
 */
public class MemoryUtils {

	public static String getMemory(){
		long maxStorage = Runtime.getRuntime().maxMemory() / 1024 / 1024;//最大内存
        long useStorage = Runtime.getRuntime().totalMemory() / 1024 / 1024;//使用内存
        long surplusStorage = Runtime.getRuntime().freeMemory() / 1024 / 1024;//剩余内存
        long maxUseStorage = (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024;//最大可用内存
        StringBuffer builder = new StringBuffer();
        builder.append("最大内存：" + maxStorage + "M,");
        builder.append("已分配内存：" + useStorage + "M,");
        builder.append("项目可用内存：" + surplusStorage + "M,");
        builder.append("最大可用内存：" + maxUseStorage + "M");
        return builder.toString();
	}
}
