package com.design.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * 
 * @author Join
 *
 */
public class IpUtils {
	/**
	 * 获取外网的IP(要访问Url，要放到后台线程里处理)
	 * 
	 * @return
	 */
	public static String getNetIp() {
		String ip = "";
		String chinaz = "http://ip.chinaz.com";
		StringBuilder inputLine = new StringBuilder();
		String read = "";
		URL url = null;
		HttpURLConnection urlConnection = null;
		BufferedReader in = null;
		try {
			url = new URL(chinaz);
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			while ((read = in.readLine()) != null) {
				inputLine.append(read + "\r\n");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
		Matcher m = p.matcher(inputLine.toString());
		if (m.find()) {
			String ipstr = m.group(1);
			ip = ipstr;
		}
		return ip;
	}

	/**
	 * 获取本地IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getLocalIP(HttpServletRequest request) {
		return request.getLocalAddr();
	}

	/**
	 * 获取ip和项目名
	 * @author JohnDeng
	 * @datatime 2018年5月2日下午5:17:39
	 * @param request
	 * @return
	 */
	public static String getIPAndPortAndProjectName(HttpServletRequest request){
		int prot=request.getLocalPort();
		String context=request.getContextPath();
		return "http://"+getNetIp()+":"+Integer.toString(prot)+context;
	}
	
	/**
	 * 获取反向代理服务器地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) { 
	       String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       return ip; 
	   }
	
}
