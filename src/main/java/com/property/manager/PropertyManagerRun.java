package com.property.manager;

import com.property.manager.mysqlmanager.MySQLManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyManagerRun {

	public static void main(String args[]) {

		MySQLManager.init();

		SpringApplication.run(PropertyManagerRun.class, args);
	}
}
