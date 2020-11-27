package com.sdstc.redisson.start.properties;

import org.redisson.client.codec.Codec;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {
	SingleServerConfig singleServerConfig;
	ClusterServersConfig clusterServersConfig;
	int threads;
	int nettyThreads;
	String transportMode;
	Codec codec;
}
