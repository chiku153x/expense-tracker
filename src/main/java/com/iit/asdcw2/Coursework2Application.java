package com.iit.asdcw2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
//@EntityScan(basePackages = {"com.iit.asdcw2.expensetracker.domain"})
@ComponentScan
public class Coursework2Application {

	public static void main(String[] args) {
		SpringApplication.run(Coursework2Application.class, args);
	}

}
