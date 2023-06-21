package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class AssociateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssociateServiceApplication.class, args);
	}
	

    @Bean
    RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
    
    @Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
