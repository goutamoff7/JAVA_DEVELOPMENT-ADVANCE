package org.example;

import java.beans.ConstructorProperties;

public class Student {
    private int id;
    private int age;
    private Laptop laptop;

    Student(){
        System.out.println("Student Object Created");
    }

    @ConstructorProperties({"id","laptop"})
    Student(int id,Laptop laptop){
        System.out.println("parameterised constructor called of Student");
        this.id = id;
        this.laptop = laptop;
    }

    public int getId() {
        return id;
    }

    public void code(){
        System.out.println("Student write Code");
        laptop.compile();
    }

    public int getAge() {
        return age;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        System.out.println("setLaptop method called of Student");
        this.laptop = laptop;
    }

    public void setAge(int age) {
        System.out.println("setAge method called of Student");
        this.age = age;
    }
}
