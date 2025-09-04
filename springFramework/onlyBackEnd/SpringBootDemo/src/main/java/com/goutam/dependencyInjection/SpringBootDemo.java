package com.goutam.dependencyInjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemo {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootDemo.class, args);
		Dev dev = context.getBean(Dev.class);
		dev.build();

	}

}
