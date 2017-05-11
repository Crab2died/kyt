package com.github.kyt.mq.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

public class ConsumerListener implements MessageListener {

	private static Logger logger = Logger.getLogger(ConsumerListener.class);

	/**
	 * {@link ConsumerListener}
	 */
	@Override
	public void onMessage(Message message) {
		logger.debug(Thread.currentThread().getName());
		if (message instanceof TextMessage) {
			TextMessage text = (TextMessage) message;
			try {
				System.out.println(text.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
