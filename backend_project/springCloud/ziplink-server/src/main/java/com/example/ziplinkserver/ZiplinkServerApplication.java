package com.example.ziplinkserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.internal.EnableZipkinServer;


@EnableZipkinServer
@SpringBootApplication
public class ZiplinkServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZiplinkServerApplication.class, args);
	}
}
