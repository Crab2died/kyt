package com.github.kyt.service.impl;


import org.springframework.stereotype.Service;

import com.github.kyt.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {

	public void Index() {
		System.out.println("HELLO INDEX!");
	}
}
