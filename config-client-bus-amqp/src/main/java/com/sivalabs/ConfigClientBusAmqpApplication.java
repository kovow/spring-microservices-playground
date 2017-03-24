package com.sivalabs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigClientBusAmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientBusAmqpApplication.class, args);
	}
}

@RestController
@RefreshScope
class QuoteController
{
	@Value("${quote}")
	private String quote;

	@GetMapping("/quote")
	public String quote()
	{
		return quote;
	}
}
