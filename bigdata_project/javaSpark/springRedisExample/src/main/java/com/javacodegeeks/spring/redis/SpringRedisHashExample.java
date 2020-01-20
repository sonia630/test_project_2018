package com.javacodegeeks.spring.redis;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

public class SpringRedisHashExample {
	public static void main(String[] args) throws URISyntaxException, Exception {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringRedisConfig.class);
		try {			
			StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("strRedisTemplate");
			HashOperations<String, String, String> hash = redisTemplate.opsForHash();
			String empJoeKey = "emp:joe";
			String empJohnKey = "emp:john";
			
			Map<String, String> empJoeMap = new HashMap<String, String>();
			empJoeMap.put("name", "xiaoming");
			empJoeMap.put("age", "32");
			empJoeMap.put("id", "01");
			
			Map<String, String> empJohnMap = new HashMap<String, String>();
			empJohnMap.put("name", "xiaoming2");
			empJohnMap.put("age", "42");
			empJohnMap.put("id", "02");
			
			hash.putAll(empJoeKey, empJoeMap);
			hash.putAll(empJohnKey, empJohnMap);	
			
			System.out.println("Get emp joe details: " + hash.entries(empJoeKey));
			System.out.println("Get emp john details: " + hash.entries(empJohnKey));
		} finally {
			ctx.close();
		}
	}
}
