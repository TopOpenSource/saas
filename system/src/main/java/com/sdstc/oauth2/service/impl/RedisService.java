package com.sdstc.oauth2.service.impl;

import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("redisService")
public class RedisService {
	@Autowired
    Redisson redisson;
	@Autowired
	RedissonClient  redissonClient;

	public String getValue(String key) {
		RBucket<String> value=redissonClient.getBucket(key);
		return value.get();
	}
	
	public <T> T getValue(String key,Class<T> valueClazz) {
		RBucket<T> value=redissonClient.getBucket(key);
		return value.get();
	}
	
	public  <T> void setValue(String key,Object value,Class<T> valueClazz) {
		RBucket<T> keyVal=redissonClient.getBucket(key);
		keyVal.set((T)value);
	}
}