package org.example;

public class Laptop{

    private String model;

    public Laptop() {
        System.out.println("Laptop Object Created");
    }

    public void compile(){
        System.out.println("Compiling using Laptop");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
