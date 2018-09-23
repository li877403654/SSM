package com.design.framework.cache.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.framework.utils.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisServiceImpl implements RedisService {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public Jedis getJedis(){
			
		return  jedisPool.getResource();
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean exists(String key) {
		Jedis jedis = null;
		boolean exists = false;
		try {
			jedis = getJedis();
			exists = jedis.exists(key);
			return exists;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exists fail");
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String set(String key, String value) {
		Jedis jedis = null;
		String set = null;
		try {
			jedis = getJedis();
			set = jedis.set((key), (value));
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String set(String key, String value, int seconds) {
		Jedis jedis = null;
		String set = null;
		try {
			jedis = getJedis();
			if (jedis != null) {
				set = jedis.setex(key, seconds, value);
				return set;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Object get(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long delete(String key) {
		Jedis jedis = null;
		Long lang = null;
		try {
			jedis =getJedis();
			jedis.del(jedis.get(key));
			return lang;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String set(byte[] keyName, Object obj) {

		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.setex(keyName, 1800, SerializeUtil.serialize(obj));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean exists(byte[] keyName) {
		Jedis jedis = null;
		boolean exists = false;
		try {
			jedis = getJedis();
			exists = jedis.exists(keyName);
			return exists;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exists fail");
			return false;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String set(byte[] keyName, byte[] value, int seconds) {
		Jedis jedis = null;
		String set = null;
		try {
			jedis = getJedis();
			set = jedis.setex(keyName, seconds, value);
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public byte[] get(byte[] keyName) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			byte[]  byt= jedis.get(keyName);
			return byt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Long delete(byte[] keyName) {
		Jedis jedis = null;
		Long lang = null;
		try {
			jedis = getJedis();
			lang = jedis.del(keyName);
			return lang;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	

}
