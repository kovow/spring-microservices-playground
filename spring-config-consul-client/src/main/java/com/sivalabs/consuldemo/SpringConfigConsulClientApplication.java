package com.sivalabs.consuldemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
public class SpringConfigConsulClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigConsulClientApplication.class, args);
	}
}

@RestController
//@RefreshScope
class HelloController
{
	@Value("${myname:siva}")
	private String myname;
	
	@Autowired
	private FtpConfigProperties ftpConfigProperties;
	
	@GetMapping("/myname")
	public String myname()
	{
		return myname;
	}
	
	@GetMapping("/ftpConfig")
	public FtpConfigProperties ftpConfig()
	{
		return ftpConfigProperties;
	}
	
	
}