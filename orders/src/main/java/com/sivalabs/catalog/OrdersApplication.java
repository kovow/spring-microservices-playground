package com.sivalabs.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sivalabs.jcart.domain.Product;

@EnableDiscoveryClient
@SpringBootApplication
public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}
}

@RestController
@RefreshScope
@EnableFeignClients
class OrderController
{
	@Autowired CatalogServiceProxy catalogServiceProxy;
	
	@GetMapping("/products")
	public List<Product> products()
	{
		List<Product> products = catalogServiceProxy.getProducts();
		System.out.println(products);
		return products;
	}
}