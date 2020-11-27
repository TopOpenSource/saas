package com.sdstc.pub.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.RedissonLock;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author cheng
 */
@Repository
public class RedisDao {
	@Autowired
	RedissonClient redissonClient;
	
	@Autowired
	RedisLock redisLock;

	/**
	 * 
	 * @param key
	 * @param lockTimeout 锁超时时间  秒
	 * @param timeout  key保存时间     秒
	 * @return
	 */
	public boolean getSet(String key,String value,long lockTimeout,long timeout) {
		boolean pass=false;
		redisLock.getLock("LOCK_"+key, lockTimeout);
		if(!(value==null?"1":value).equals(this.getValue(key))) {
			pass=true;
			this.setKey(key, (value==null?"1":value), timeout*1000);
		}else {
			pass=false;
		}
		redisLock.unclock("LOCK_"+key);
		return pass;
		
	}
	
	public void setKey(String key, String value) {
		RBucket<String> keyVal = redissonClient.getBucket(key);
		keyVal.set(value);
	}
	
	public void setKey(String key, String value, long timeout) {
		RBucket<String> keyVal = redissonClient.getBucket(key);
		keyVal.set(value, timeout, TimeUnit.MILLISECONDS);
	}

	public String getValue(String key) {
		RBucket<String> value = redissonClient.getBucket(key);
		return value.get();
	}

	public <T> T getValue(String key, Class<T> valueClazz) {
		RBucket<T> value = redissonClient.getBucket(key);
		return value.get();
	}

	public <T> void setValue(String key, Object value, Class<T> valueClazz) {
		RBucket<T> keyVal = redissonClient.getBucket(key);
		keyVal.set((T) value);
	}

	public void delKey(String key) {
		RBucket keyVal = redissonClient.getBucket(key);
		keyVal.delete();
	}
	
	public <T> void setValue(String key, Object value, Class<T> valueClazz, long timeout) {
		RBucket<T> keyVal = redissonClient.getBucket(key);
		keyVal.set((T) value, timeout, TimeUnit.MILLISECONDS);
	}


}