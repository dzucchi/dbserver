package com.dbserver.dbserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DbserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbserverApplication.class, args);
	}

}
