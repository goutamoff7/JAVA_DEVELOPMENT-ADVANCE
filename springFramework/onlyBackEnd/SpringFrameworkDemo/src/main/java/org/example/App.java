package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        // this line creates a container for us where beans (objects) will be stored
        // also the object mentioned in the xml file is created here if the bean scope is singleton
        // if the bean scope is prototype the object creation will occur when context.getBean called
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        // Bean name = small letter of Class names, object reference is assigned to reference var. developer1
        // By default the Bean Scope is Singleton, means every time context.getBean called will return same ref
//        Developer developer1 = (Developer) context.getBean("developer1");
//        developer1.age = 27;
//        System.out.println(developer1.age);

//        Developer developer2 = (Developer) context.getBean("developer1");
        // same age = 27 as the object is of developer1 Class with singleton scope
//        System.out.println(developer2.age);

        // Bean Scope changed to prototype, means every time context.getBean called will return diff ref
        Developer developer3 = (Developer) context.getBean("developer");
        developer3.age = 27;
        System.out.println("Developer age :"+ developer3.age);

        Developer developer4 = (Developer) context.getBean("developer");
        // same age = 0 as the object of developer3 Class is of prototype scope
        System.out.println("Developer age :"+ developer4.age);


        Student student = (Student) context.getBean("student");
        System.out.println("Student id : "+student.getId()); // id initialize using Constructor Injection
//        student.setAge(27); // using setter injection in xml the value gets assigned
        System.out.println("Student age : "+student.getAge());
        student.code();


    }
}
