package com.sdstc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class GateWayStart {
	public static void main(String[] args) {
		SpringApplication.run(GateWayStart.class, args);
	}
}
