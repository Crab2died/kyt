package com.github.kyt.common.mq.producer.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.github.kyt.common.mq.producer.ProducerService;

@Component("ProducerService")
public class ProducerServiceImpl implements ProducerService{

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendObjMsg(Destination destination, final String obj) {
        jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(obj);
			}
		});// 需要定义自己的MessageConverter 或者是使用默认的
    }
}
