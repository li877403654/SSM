package com.design.framework.cache.service;

import redis.clients.jedis.Jedis;

public interface RedisService extends BaseCacheServer {

	/**
	 * 序列化保存对象，key和value
	 * 
	 * @param keyName
	 * @param objectValue
	 * @return
	 * @author John
	 * @datatime 2017年12月7日下午4:15:46
	 */
	public String set(byte[] keyName, Object obj);

	public boolean exists(byte[] keyName);

	public String set(byte[] keyName, byte[]  value, int seconds);

	public byte[] get(byte[] keyName);

	public Long delete(byte[] keyName);
	
	public Jedis getJedis() ;
	
	

}