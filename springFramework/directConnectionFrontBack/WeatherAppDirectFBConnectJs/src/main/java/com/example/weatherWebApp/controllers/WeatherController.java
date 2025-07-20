package com.example.weatherWebApp.controllers;

import com.example.weatherWebApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController
{
    @Autowired
    WeatherService weatherService;

    // localhost:8080/weather  -> GET request

    @GetMapping("/weather")
    public ResponseEntity<Map<String,Object>> submitForm(@RequestParam String cityName)
    {
        Map<String, Object> weatherData = weatherService.getWeatherInfo(cityName);
        return new ResponseEntity<>(weatherData,HttpStatus.OK);
    }


}
