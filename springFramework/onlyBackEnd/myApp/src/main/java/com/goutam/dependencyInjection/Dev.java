package com.goutam.dependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Dev
{
   // Field Injection must use @Autowired annotation
   // applicable for loose coupling also as spring find the object by Type
    //which object it will choose Laptop or Desktop, lets take Desktop object
    //remove @Component from Desktop or add @Primary to any one Class or add @Qualifier with Bean name here
    //By default Bean name is same as class name starts with small letter
    @Autowired
    @Qualifier("desktop")
    private Computer comp;

    //Constructor Injection without @Autowired annotation
    //Automatically getting the object with parameter name as Bean name
    private Computer lap;
    public Dev(Computer laptop)
    {
        this.lap =laptop;
    }

    //Setter Injection must use @Autowired annotation
    //Automatically getting the object with parameter name as Bean name
    private Computer desk;
    @Autowired
    public void setComputer(Computer desktop)
    {
        this.desk = desktop;
    }

    public void build()
    {
        comp.compile();
        lap.compile();
        desk.compile();
        System.out.println("Working on Awesome Project");
    }
}
