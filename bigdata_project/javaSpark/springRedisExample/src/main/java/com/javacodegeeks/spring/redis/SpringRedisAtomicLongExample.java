package com.javacodegeeks.spring.redis;

import java.net.URISyntaxException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

public class SpringRedisAtomicLongExample {
	public static void main(String[] args) throws URISyntaxException, Exception {
		ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
				SpringRedisConfig.class);
		try {			
			RedisConnectionFactory connectionFactory = (RedisConnectionFactory) ctx.getBean("connectionFactory");
			RedisAtomicLong uniqueNbr =
					new RedisAtomicLong("RedisUniqueNbr", connectionFactory, 0);
			System.out.println("Redis Atomic Long: " + uniqueNbr.incrementAndGet());
			
		} finally {
			ctx.close();
		}
	}
}
