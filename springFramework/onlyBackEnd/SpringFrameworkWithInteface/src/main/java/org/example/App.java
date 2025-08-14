package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        Developer developer = (Developer) context.getBean("developer");
        System.out.println("Developer age : " + developer.getAge());
        developer.code();

        // By this way also we can avoid Typecasting
        Desktop desktop = context.getBean("com2", Desktop.class);
        desktop.compile();

        //  By this way also we can avoid Typecasting and also skip name field,
        // in the bean configuration of Mobile class we can also skip id field
        Mobile mobile = context.getBean(Mobile.class);
        mobile.compile();

//        Here the Laptop object is loaded as the Laptop bean is mentioned as primary
        Computer computer = context.getBean(Computer.class);
        computer.compile();


    }
}
