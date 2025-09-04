package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Developer {

    public Developer() {
        System.out.println("Developer Object Created");
    }

    @Value("27")
    private int age;

    @Autowired
    private Computer computer;

    public void code(){
        System.out.println("Coding");
        computer.compile();

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}
