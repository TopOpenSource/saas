package com.sdstc.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author cheng
 * 网关服务
 */
@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
@EnableFeignClients
public class GatewayStart {
    public static void main(String[] args) {
        SpringApplication.run(GatewayStart.class, args);
    }
}
