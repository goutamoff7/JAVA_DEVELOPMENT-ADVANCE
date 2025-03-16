package com.goutam.weatherApp.controller;

import com.goutam.weatherApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilityController {

    @Autowired
    AppCache appCache;

    @GetMapping("/clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
