package com.example.springbootboostrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringBootBoostrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBoostrapApplication.class, args);
	}

}
