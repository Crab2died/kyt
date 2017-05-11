package com.github.kyt.common.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.github.kyt.common.mq.consumer.ConsumerListener;

public class MQListener {

	static {
		JmsTemplate jmsTemplate = new JmsTemplate();
		ActiveMQConnectionFactory conn = new ActiveMQConnectionFactory();
		conn.setBrokerURL("failover:(tcp://127.0.0.1:61616)");
		PooledConnectionFactory pool = new PooledConnectionFactory();
		pool.setConnectionFactory(conn);
		pool.setMaxConnections(3);
		DefaultMessageListenerContainer msgListener = new DefaultMessageListenerContainer();
		msgListener.setConnectionFactory(pool);
		msgListener.setDestination(new ActiveMQQueue("queen"));
		msgListener.setMessageListener(new ConsumerListener());
	}
}
