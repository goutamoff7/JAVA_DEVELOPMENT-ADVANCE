package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        Developer developer = context.getBean(Developer.class);
        developer.code();
        developer.getLaptop().setModel("Lenovo");
        System.out.println(developer.getLaptop().getModel());  // Lenovo

        Programmer programmer = context.getBean(Programmer.class);
        programmer.code();
        // this laptop object is different
        System.out.println(programmer.getLaptop().getModel()); // null

    }
}
