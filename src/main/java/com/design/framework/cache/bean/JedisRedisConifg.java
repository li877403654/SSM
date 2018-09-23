package com.design.framework.cache.bean;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;
/**
 * redis缓冲实体配置
 * @author JohnDeng
 * @datatime 2018年7月6日下午5:38:11
 */
@Component
public class JedisRedisConifg extends JedisPoolConfig {

	@Value("#{prop.redis_host}")
	private String host;// Redis服务器IP
	@Value("#{prop.redis_port}")
	private int port;// Redis的端口号
	@Value("#{prop.redis_password}")
	private String password;// 访问密码
	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	@Value("#{prop.redis_maxActive}")
	private int maxActive;
	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	@Value("#{prop.redis_maxIdle}")
	private int maxIdle;
	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	@Value("#{prop.redis_maxWait}")
	private int maxWait;
	@Value("#{prop.redis_timeOut}")
	private int timeout;
	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	@Value("#{prop.redis_testOnBorrow}")
	private boolean testOnBorrow;
	
	private Set<HostAndPort> hapSet = new LinkedHashSet<HostAndPort>();// 集群IP地址集合

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Set<HostAndPort> getHapSet() {
		return hapSet;
	}

	public void setHapSet(Set<HostAndPort> hapSet) {
		this.hapSet = hapSet;
	}

	@Override
	public String toString() {
		return "JedisConifg [host=" + host + ", port=" + port + ", password=" + password + ", maxActive=" + maxActive
				+ ", maxIdle=" + maxIdle + ", maxWait=" + maxWait + ", timeout=" + timeout + ", testOnBorrow="
				+ testOnBorrow + ", hapSet=" + hapSet + "]";
	}

}
