package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class FrenchGreetingService implements GreetingService {
    @Override
    public void greet(String name)
    {
        System.out.println("Bonjour "+name);
    }
}
