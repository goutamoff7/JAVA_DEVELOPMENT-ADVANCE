package com.goutam.dependencyInjection;

import org.springframework.stereotype.Component;

@Component
//@Primary
public class Desktop implements Computer {
    @Override
    public void compile()
    {
        System.out.println("No bugs found by Desktop");
    }
}
