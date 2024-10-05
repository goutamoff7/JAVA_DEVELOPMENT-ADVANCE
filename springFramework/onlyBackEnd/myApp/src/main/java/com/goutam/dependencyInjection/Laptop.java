package com.goutam.dependencyInjection;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {
    @Override
    public void compile()
    {
        System.out.println("No bugs found by Laptop");
    }
}
