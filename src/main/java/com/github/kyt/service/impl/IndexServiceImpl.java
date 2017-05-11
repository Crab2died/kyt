package com.github.kyt.service.impl;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.kyt.mq.producer.ProducerService;
import com.github.kyt.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private Destination queueDestination;

	public void Index() {
		producerService.sendObjMsg(queueDestination, "one msg");
		System.out.println("HELLO INDEX!");
	}
}
