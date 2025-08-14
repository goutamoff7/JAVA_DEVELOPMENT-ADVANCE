package org.example;

public class Developer {

    public Developer() {
        System.out.println("Developer Object Created");
    }

    private int age;
    private Laptop laptop;

    public void code() {
        System.out.println("Coding");
        laptop.compile();

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }


}
