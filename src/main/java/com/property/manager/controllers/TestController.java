package com.property.manager.controllers;

import java.sql.Connection;

import com.property.manager.mysqlmanager.MySQLManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/asdf")
	public String index() {

		LOGGER.info("First test is a go!");

		MySQLManager.init();
		MySQLManager mManager = MySQLManager.get();
		mManager.createConnection();
		Connection mConn = mManager.getConnection();

		return "Greetings from Spring Boot!";
	}
}

