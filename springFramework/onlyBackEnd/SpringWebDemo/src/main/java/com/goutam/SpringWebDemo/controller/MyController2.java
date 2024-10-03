package com.goutam.SpringWebDemo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController2
{
    List<String> list = new ArrayList<>();

    //localhost:8080/insert/anything
    @GetMapping("/insert/{name}")
    public String getInsert(@PathVariable String name)
    {
        list.add(name);
        return name+" added Successfully";
    }

    //localhost:8080/find?name=anything
    @GetMapping("/find")
    public String getFindName(@RequestParam String name)
    {
            if(list.contains(name))
                return name+" Found";
            else
                return name+" Not Found";
    }

    //localhost:8080/enter?name=anything
    @PostMapping(path = "/enter")
    public void postInsert(@RequestParam String name)  // in PostMapping @PathVariable can't be used
    {
        list.add(name);
        System.out.println(name+" added Successfully");
    }
}
