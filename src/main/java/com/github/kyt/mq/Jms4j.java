package com.github.kyt.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


public class Jms4j {

	private static JmsTemplate jmsTemplate;

	static {
		jmsTemplate = new JmsTemplate();
		ActiveMQConnectionFactory conn = new ActiveMQConnectionFactory();
		conn.setBrokerURL("failover:(tcp://127.0.0.1:61616)");
		PooledConnectionFactory pool = new PooledConnectionFactory();
		pool.setConnectionFactory(conn);
		pool.setMaxConnections(3);
		jmsTemplate.setMessageConverter(new ObjectConverter());
		jmsTemplate.setConnectionFactory(pool);
	}

	public static void main(String[] args) {
		final ActiveMQTopic d = new ActiveMQTopic("topic");
		final ActiveMQQueue q = new ActiveMQQueue("queen");
		for (int i = 0; i < 2; i++) {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						jmsTemplate.setPubSubNoLocal(true);
						System.out.println(Jms4j.jmsTemplate.receive(d));
						System.out.println(Jms4j.jmsTemplate.receive(q));
					}
				}
			});
			th.start();
		}
		
		for (int i = 0; i < 3; i++) {
			System.out.println("topic" + i);
			Jms4j.jmsTemplate.send(d, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createObjectMessage("this is test topic :"+Math.random());
				}
			});
		}
		
		for (int i = 0; i < 3; i++) {
			Jms4j.jmsTemplate.send(q, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createObjectMessage("this is test queen :"+Math.random());
				}
			});
		}
	}
}
