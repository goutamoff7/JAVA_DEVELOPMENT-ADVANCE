package com.goutam.CarSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CarSpringProjectApplication {

	public static void main(String[] args) 
	{
		ConfigurableApplicationContext context = SpringApplication.run(CarSpringProjectApplication.class, args);
		Bike c1 = context.getBean(Bike.class);
		System.out.println("Bike Name : "+ c1.getName()+
				"\nBike Colour : "+ c1.getColour()+
				"\nEngine Model : "+c1.getEngine().getEngineModel()+
				"\nEngine cc : "+c1.getEngine().getCc());

	}

}
