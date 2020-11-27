package com.sdstc.redisson.start.properties;

import lombok.Data;

@Data
public class SingleServerConfig extends BaseConfig{
	private String address;
	private int subscriptionConnectionMinimumIdleSize = 1;
	private int subscriptionConnectionPoolSize = 50;
	private int connectionMinimumIdleSize = 24;
	private int connectionPoolSize = 64;
	private int database = 0;
	private long dnsMonitoringInterval = 5000;
}