package com.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.rest.jedis.JedisClient;

/**
 * redis整合spring 测试类
* <p>Title: RedisClientSpring.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2020<／p>
* <p>Company: CUIT<／p>
* @author MrGeek
* @date 2020-10-16_08:30:03
* @version 1.0
 */
public class RedisClientSpring {

	/**
	 * redis 客户端单机版整合测试
	* <p>Title: testjedisSingle<／p>
	* <p>Description: <／p>
	* @throws Exception
	 */
	@Test
	public void testjedisSingle() throws Exception {
		//创建一个srpring 容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//从容器中获取对象
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		//调用jedis
		jedisClient.set("keys", "1000");
		String value = jedisClient.get("keys");
		System.out.println(value);
	}
}
