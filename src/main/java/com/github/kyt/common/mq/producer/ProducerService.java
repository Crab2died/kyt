package com.github.kyt.common.mq.producer;

import javax.jms.Destination;

public interface ProducerService {

	void sendObjMsg(Destination destination, String obj);
}
