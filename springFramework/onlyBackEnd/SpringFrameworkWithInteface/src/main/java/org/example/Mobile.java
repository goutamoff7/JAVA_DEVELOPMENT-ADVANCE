package org.example;

public class Mobile implements Computer{

    public Mobile() {
        System.out.println("Mobile Object Created");
    }

    @Override
    public void compile(){
        System.out.println("Compiling using Mobile");
    }
}
