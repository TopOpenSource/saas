package com.sdstc.redisson.start;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.yaml.snakeyaml.Yaml;

import com.sdstc.redisson.start.properties.RedissonProperties;

@Configuration
@ConditionalOnClass({ Redisson.class, RedisOperations.class })
@AutoConfigureBefore(RedisAutoConfiguration.class)
@EnableConfigurationProperties({ RedissonProperties.class, RedisProperties.class })
public class RedissonAutoConfiguration {

	@Autowired
	private RedissonProperties redissonProperties;

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	@ConditionalOnMissingBean(StringRedisTemplate.class)
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}

	@Bean
	@ConditionalOnMissingBean(RedisConnectionFactory.class)
	public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
		return new RedissonConnectionFactory(redisson);
	}

	@Bean(destroyMethod = "shutdown")
	@ConditionalOnMissingBean(RedissonClient.class)
	public RedissonClient redisson() throws IOException {
		Yaml yaml = new Yaml();
		Config config = Config.fromYAML(yaml.dump(redissonProperties));
		return Redisson.create(config);
	}

}
