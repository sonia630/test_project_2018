package com.javacodegeeks.spring.redis;

import java.net.URISyntaxException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class SpringStringRedisExample {
	public static void main(String[] args) throws URISyntaxException, Exception {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringRedisConfig.class);
		try {			
			StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("strRedisTemplate");
			ValueOperations<String, String> values = redisTemplate.opsForValue();
			values.set("01", "Joe");
			values.set("02", "John");
			
			System.out.println("Employee added: " + values.get("01"));
			System.out.println("Employee added: " + values.get("02"));
		} finally {
			ctx.close();
		}
	}
}
