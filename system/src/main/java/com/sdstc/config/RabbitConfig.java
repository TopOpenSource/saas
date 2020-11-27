package com.sdstc.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	public static final String TOPIC_EXCHANGE = "exchange1";
	public static final String QUEUE_ORDER = "queue_order";
	public static final String ROUTER_ORDER = "create.order";

	/**
	 * 声明交换机
	 * @return
	 */
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(TOPIC_EXCHANGE);
	}
	/**
	 * 声明队列
	 * @return
	 */
	@Bean
	public Queue topicQueue1() {
		return new Queue(QUEUE_ORDER);
	}
	/**
	 * 绑定
	 * @return
	 */
	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTER_ORDER);
	}

}
