package org.example;

import org.example.service.GreetingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        // applicationContext object act as a security guard and org.example contains all the Objects called beans
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example");

        //class name inside getBean() - first character of the class name should be in small letter
        GreetingService greetingService = (GreetingService)applicationContext.getBean("englishGreetingService");
        greetingService.greet("Goutam");
        greetingService = (GreetingService)applicationContext.getBean("frenchGreetingService");
        greetingService.greet("Goutam");

    }
}
