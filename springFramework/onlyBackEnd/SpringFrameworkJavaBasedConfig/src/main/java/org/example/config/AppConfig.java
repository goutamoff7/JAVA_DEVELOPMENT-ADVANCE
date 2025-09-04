package org.example.config;

import org.example.Computer;
import org.example.Desktop;
import org.example.Developer;
import org.example.Laptop;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig {

    @Bean
    public Desktop desktop() {
        return new Desktop();
    }

    @Bean
    public Desktop desktop1() {
        return new Desktop();
    }

    @Bean(name = {"com1", "com2"})
    @Scope("prototype")
    public Desktop desktop2() {
        return new Desktop();
    }

    @Bean
    @Primary
    public Laptop laptop() {
        return new Laptop();
    }

    @Bean
    public Developer developer(@Qualifier("desktop")  Computer computer) {
        Developer developer = new Developer();
        developer.setAge(27);
        developer.setComputer(computer);
        return developer;
    }
}
