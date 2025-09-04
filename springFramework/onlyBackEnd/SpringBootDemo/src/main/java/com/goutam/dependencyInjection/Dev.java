package com.goutam.dependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Dev
{
    @Value("${dev.age}")
    int age;

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
    // (Bean name of Laptop class is given as lap
    private final Computer lap;
    public Dev(Computer lap)
    {
        this.lap =lap;
    }

    //Setter Injection must use @Autowired annotation
//    Spring always starts injection by type.
//    If there are multiple beans of that type:
//      It checks for a @Qualifier.
//      If no @Qualifier, it checks for a @Primary bean.
//      If neither is found, it tries to match the parameter name (or field name) to the bean name.
//    If still no match → exception.

//    so giving random name to the parameter can leads to ambiguity - requires Qualifier or Primary
//    else provide bean name as parameter

//    private Computer desk;
//    @Autowired
//    @Qualifier("desktop")
//    public void setComputer(Computer computer)
//    {
//        this.desk = computer;
//    }

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
        System.out.println("Dev age : "+age);
        System.out.println("Working on Awesome Project");
    }
}
