package com.taotao.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis客户端测试
* <p>Title: JedisTest.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-14_12:31:12
* @version 1.0
 */
public class JedisTest {
	
	/**
	 * redis 单机版测试
	* <p>Title: testRedisSingle<／p>
	* <p>Description: <／p>
	* @throws Exception
	 */
	@Test
	public void testRedisSingle() throws Exception {
		Jedis jedis = new Jedis("112.124.28.54",6379);
		jedis.auth("20020920z");
		jedis.set("test", "hello redis");
		String string = jedis.get("test");
		System.out.println(string);
		jedis.close();
	}
	
	/**
	 * redis 单机版测试：使用连接池
	* <p>Title: testJedisPool<／p>
	* <p>Description: <／p>
	* @throws Exception
	 */
	@Test
	public void testJedisPool() throws Exception {
		JedisPool jedisPool = new JedisPool("112.124.28.54",6379);
		//从连接池中获取连接
		Jedis jedis = jedisPool.getResource();
		jedis.auth("20020920z");
		String result = jedis.get("test");
		System.out.println(result);
		//关闭jedis以及连接池
		jedis.close();
		jedisPool.close();
	}
	
	/**
	 * Redis 客户端集群版
	* <p>Title: testJedisCluster<／p>
	* <p>Description: <／p>
	* @throws Exception
	 */
	@Test
	public void testJedisCluster() throws Exception {
		//创建一个JedisCluster对象
		Set<HostAndPort>nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("112.124.28.54", 7001));
		nodes.add(new HostAndPort("112.124.28.54", 7002));
		nodes.add(new HostAndPort("112.124.28.54", 7003));
		nodes.add(new HostAndPort("112.124.28.54", 7004));
		nodes.add(new HostAndPort("112.124.28.54", 7005));
		nodes.add(new HostAndPort("112.124.28.54", 7006));
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(300);
		
		String password = "20020920z";
		
		//在nodes 中指定每个节点的地址
		JedisCluster jedisCluster = new JedisCluster(nodes,3000,3000,5,password,poolConfig);
		jedisCluster.set("name", "zhangshan");
		jedisCluster.set("value", "100");
		String name = jedisCluster.get("name");
		String value = jedisCluster.get("value");
		System.out.println("name:"+name);
		System.out.println("value:"+value);
		//关闭连接
		jedisCluster.close();
	}
}
