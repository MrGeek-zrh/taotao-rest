package com.taotao.rest.jedis;

/**
 * jedis客户端
* <p>Title: JedisClientSingle.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-15_19:16:44
* @version 1.0
 */
public interface JedisClient {

	public String set(String key,String value);
	
	public String get(String key);
	
	public Long hset(String key,String item,String value);
	
	public String hget(String key,String item);
	
	public Long incr(String key);
	
	public Long decr(String key);
	
	public Long expire(String key,int second);
	
	public Long ttl(String key);
	
	public Long hdel(String key,String item);
}
