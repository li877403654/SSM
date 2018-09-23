package com.design.framework.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 项目启动监听器
 * 
 * @author JohnDeng
 * @date 2018年8月8日下午3:37:06
 */
public class WebContextListener extends ContextLoader implements ServletContextListener {
	protected Logger logger = Logger.getLogger(getClass());

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 读取欢迎页面
		InputStream banner = this.getClass().getResourceAsStream("/template/banner.txt");
		try {
			System.out.println(new String(IOUtils.toByteArray(banner, banner.available())));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// 获取redis
		Properties prop = new Properties();
		InputStream is = this.getClass().getResourceAsStream("/config/redis.properties");
		try {
			prop.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.info("*************file not find**************");
		}
		String host = prop.getProperty("redis_host");
		int maxIdle = Integer.parseInt(prop.getProperty("redis_maxIdle"));
		int maxTotal = Integer.parseInt(prop.getProperty("redis_maxActive"));
		int minIdle = Integer.parseInt(prop.getProperty("redis.minIdle"));
		boolean testOnBorrow = Boolean.parseBoolean(prop.getProperty("redis_testOnBorrow"));
		int port = Integer.parseInt(prop.getProperty("redis_port"));
		int timeOut = Integer.parseInt(prop.getProperty("redis_timeOut"));
		String password = prop.getProperty("redis_password");
		// 判断是否有连接Reids
		Jedis jedis = null;
		JedisPool jedisPool = null;
		try {
			if (jedisPool == null) {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxIdle(maxIdle);
				config.setMaxTotal(maxTotal);
				config.setMinIdle(minIdle);
				config.setTestOnBorrow(testOnBorrow);
				jedisPool = new JedisPool(config, host, port, timeOut, password);
				jedis = jedisPool.getResource();
			}
			jedis.ping();
			logger.info("*************redis connection success**************");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("*************redis connection error**************");
		} finally {
			if (jedisPool != null) {
				jedisPool.close();
			}
			if (jedis != null) {
				jedis.close();
			}
		}

	}
}
