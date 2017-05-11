package com.github.kyt.controller;

import com.github.kyt.pojo.dto.User;
import com.github.kyt.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.kyt.service.IndexService;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Autowired
	private IndexService indexservice;

	@Autowired
	private UserService userService;

	private static Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping(value = "index", method = RequestMethod.GET, name = "首页")
	public String index() {
		logger.debug("welcome to index!");
		//indexservice.Index();
		User user = userService.getById(1);
		User nuser = new User();
		nuser.setUname("test");
		nuser.setUpass("111");
		userService.insert(nuser);
		System.out.println(user.getUname());
		System.out.println(nuser.getId());
		return "index";
	}
}
