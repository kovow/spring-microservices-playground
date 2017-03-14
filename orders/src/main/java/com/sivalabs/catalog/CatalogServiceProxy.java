package com.sivalabs.catalog;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sivalabs.jcart.domain.Product;

@FeignClient(name="products-proxy")
@RibbonClient(name="products")
public interface CatalogServiceProxy {
	@RequestMapping(value="/products", method=RequestMethod.GET)
	List<Product> getProducts();
}
