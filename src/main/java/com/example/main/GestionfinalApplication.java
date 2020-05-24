package com.example.main;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = {"com.example.*", "com.example.main.JerseyConfig", "com.example.controlador.*"})
@EntityScan(basePackages = {"com.example"} ) 
@EnableJpaRepositories(basePackages = {"com.example"}) 
public class GestionfinalApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		 new GestionfinalApplication().configure(new SpringApplicationBuilder(GestionfinalApplication.class)).run(args);
	}
	
}