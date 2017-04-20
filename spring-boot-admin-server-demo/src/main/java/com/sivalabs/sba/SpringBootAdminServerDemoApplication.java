package com.sivalabs.sba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminServerDemoApplication.class, args);
	}
}
