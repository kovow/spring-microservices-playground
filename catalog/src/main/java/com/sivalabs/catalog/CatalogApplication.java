package com.sivalabs.catalog;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sivalabs.jcart.domain.Product;

@EnableDiscoveryClient
@SpringBootApplication
public class CatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}
}

@RestController
@RefreshScope
class DemoController
{
	@Value("${catalog.3rdparty.access.enabled}")
	private String tpaEnabled;
	
	@GetMapping("/demo")
	public String demo()
	{
		System.out.println("catalog.3rdparty.access.enabled="+tpaEnabled);
		return "OK";
	}
	
	@GetMapping("/products")
	public List<Product> listProducts()
	{
		return Arrays.asList(
				new Product(1L, "Product 1", 25.0),
				new Product(2L, "Product 2", 45.0)
				
				);
	}
}