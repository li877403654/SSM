package com.design.framework.utils;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.design.framework.cache.bean.JedisRedisConifg;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis缓冲工具类
 * 
 * @author JohnDeng
 * @datatime 2018年5月2日下午5:24:00
 */
@Component
public final class RedisUtil {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JedisRedisConifg jedisRedisConifg;
	
	private  JedisPool jedisPool = null;
	
	public RedisUtil(){
		
	}

	@PostConstruct 
	//指定该方法在对象被创建后马上调用 相当于配置文件中的init-method属性
	private  void init() {
		if (jedisPool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(jedisRedisConifg.getMaxIdle());
			config.setTestOnBorrow(jedisRedisConifg.getTestOnBorrow());
			jedisPool = new JedisPool(config, jedisRedisConifg.getHost(), 
					jedisRedisConifg.getPort(),jedisRedisConifg.getTimeout(),jedisRedisConifg.getPassword());
		}
	}
	
	

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized  Jedis getJedis() {
		try {
			if (jedisPool != null) {
				return jedisPool.getResource();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	public  void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}



}