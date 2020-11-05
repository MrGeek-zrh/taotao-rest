package com.taotao.rest.jedis.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.rest.jedis.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * jedis 客户端单机版
* <p>Title: JedisClientSingle.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-15_19:21:56
* @version 1.0
 */
public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;
	
	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String key, String item, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		Long result = jedis.hset(key, item, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String item) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		String result = jedis.hget(key, item);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		Long result = jedis.decr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String item) {
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		Long result = jedis.hdel(key, item);
		jedis.close();
		return result;
	}

}
