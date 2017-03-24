package com.sivalabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RibbonLbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonLbDemoApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}

@RestController
class QuoteProxyController
{
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/myquote")
	public String quote()
	{
		String url = "http://quote-service/quote";
		String quote = restTemplate.getForObject(url, String.class);
		return quote;
	}
}
