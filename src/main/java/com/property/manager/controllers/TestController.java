package com.property.manager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/")
	@ResponseBody
	public String index() {

		LOGGER.info("First test is a go!");

		return "Greetings from Spring Boot!";
	}
}

