package com.sdstc.pub.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("redisLock")
public class RedisLock {
	@Autowired
	Redisson redisson;
	
	/**
	 * 加锁
	 * @param key
	 * @param value
	 * @param timeout seconds
	 * @return
	 */
	public RLock getLock(String key,Long timeout) {
		RLock rlock=redisson.getLock(key);
		rlock.lock(timeout, TimeUnit.SECONDS);
		return rlock;
	}
	
	/**
	 * 释放锁
	 * @param key
	 */
	public void unclock(String key) {
		RLock rlock=redisson.getLock(key);
		//释放之前先验证
		if(rlock.isLocked()) {
			rlock.unlock();
		}
	}
}
