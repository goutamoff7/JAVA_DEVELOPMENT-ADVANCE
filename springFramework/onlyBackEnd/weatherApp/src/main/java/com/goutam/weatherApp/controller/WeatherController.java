package com.goutam.weatherApp.controller;

import com.goutam.weatherApp.model.WeatherResponse;
import com.goutam.weatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/city/{city}")
    public ResponseEntity<?> getWeatherByCity(@PathVariable String city) {
        try{
            WeatherResponse weatherResponse = weatherService.getWeatherByCity(city);
            if (weatherResponse != null)
                return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}


