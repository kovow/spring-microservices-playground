package com.sivalabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClient1Application.class, args);
	}
}

@RestController
class TemperatureController
{
	@GetMapping("/temperature")
	public String temperature()
	{
		System.out.println("--------getting temperature-----------");
		return "Temperature: "+new Random().nextInt()+" degrees cc";
	}

}
