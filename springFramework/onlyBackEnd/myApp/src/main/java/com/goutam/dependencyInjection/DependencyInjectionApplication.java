package com.goutam.dependencyInjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {

	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);
		Dev dev = context.getBean(Dev.class);
		dev.build();
	}

}
