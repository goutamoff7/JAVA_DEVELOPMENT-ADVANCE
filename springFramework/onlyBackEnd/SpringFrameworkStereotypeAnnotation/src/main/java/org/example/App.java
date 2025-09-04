package org.example;

import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // method name in AppConfig is desktop = the bean name (default)
        Desktop desktop = (Desktop) context.getBean("desktop");
        desktop.compile();

        Desktop desktop2 = context.getBean(Desktop.class);
        desktop2.compile();

        // cause ambiguity as it search beans based on Type, and found more than one bean of same type
        // so use @Primary annotation to mention the primary bean
        Computer computer = context.getBean(Computer.class);
        computer.compile();

        Developer developer = context.getBean(Developer.class);
        System.out.println("Developer age : "+developer.getAge());
        developer.code();
    }
}
