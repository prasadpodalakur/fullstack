package org.example.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "org.example.demo")
public class SpringConfig {
	
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/hr");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setDriverClassName(" com.mysql.cj.jdbc.Driver");
		return dataSource;
	}

}
