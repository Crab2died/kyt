package com.github.kyt.mq.producer;

import javax.jms.Destination;

public interface ProducerService {

	void sendObjMsg(Destination destination, String obj);
}
