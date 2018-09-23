package com.design.framework.cache.service;

public interface BaseCacheServer {
	public boolean exists(String key);
	
	public String set(String key, String value);

	public String set(String key, String value,  int seconds);

	public Object get(String key);

	public Long delete(String key);
}
