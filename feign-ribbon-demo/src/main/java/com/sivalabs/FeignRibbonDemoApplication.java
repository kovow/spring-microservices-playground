package com.sivalabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FeignRibbonDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignRibbonDemoApplication.class, args);
	}
}

@RestController
@EnableFeignClients
class QuoteProxyController
{
	@Autowired
	private QuoteServiceProxy quoteServiceProxy;

	@Autowired
	private RibbonQuoteServiceProxy ribbonQuoteServiceProxy;

	@GetMapping("/myquote")
	public String quote()
	{
		String quote = quoteServiceProxy.getQuote();
		return quote;
	}

	@GetMapping("/todayquote")
	public String todayquote()
	{
		String quote = ribbonQuoteServiceProxy.getQuote();
		return quote;
	}
}

@FeignClient(name="quote-proxy", url="localhost:8096")
interface QuoteServiceProxy
{
	@RequestMapping(value = "/quote", method= RequestMethod.GET)
	String getQuote();
}

@FeignClient(name="quotes")
@RibbonClient
interface RibbonQuoteServiceProxy
{
	@RequestMapping(value = "/quote", method= RequestMethod.GET)
	String getQuote();
}