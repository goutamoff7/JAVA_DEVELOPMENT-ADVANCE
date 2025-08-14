package org.example;

public class Programmer {

    public Programmer() {
        System.out.println("Programmer Object Created");
    }

    private Laptop laptop;

    public void code() {
        System.out.println("Programming");
        laptop.compile();
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
