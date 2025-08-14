package org.example;

public class Developer {

    public Developer() {
        System.out.println("Developer Object Created");
    }

    private int age;
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
