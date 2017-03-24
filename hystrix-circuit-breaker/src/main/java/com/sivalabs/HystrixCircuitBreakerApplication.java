package com.sivalabs;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class HystrixCircuitBreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixCircuitBreakerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}

@RestController
class HystrixDemoController
{
	@Autowired
	HystrixDemoComponent hystrixDemoComponent;

	@GetMapping("/tempsafe")
	public String tempsafe()
	{
		return hystrixDemoComponent.getTemp();
	}

}

@Component
class HystrixDemoComponent
{
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getDefaultTemp")
	public String getTemp(){
		String temp = restTemplate.getForObject("http://eureka-client2/tempnow", String.class);
		return temp;
	}

	public String getDefaultTemp(){
		return "Temperature : 0 degrees cc";
	}
}