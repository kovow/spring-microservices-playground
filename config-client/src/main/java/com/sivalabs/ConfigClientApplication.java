package com.sivalabs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}
}

@RestController
@RefreshScope
class ConfigClientDemoController
{
	@Value("${profile-service-url}")
	private String profileServiceUrl;

	@GetMapping("/demo")
	public String demo()
	{
		//System.err.println("profile-service-url: "+profileServiceUrl);
		return profileServiceUrl;
	}
}