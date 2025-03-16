package com.goutam.weatherApp.controller;

import com.goutam.weatherApp.model.WeatherResponse;
import com.goutam.weatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    //http://localhost:8080/get-weather?city=durgapur
    @GetMapping("get-weather")
    public ResponseEntity<?> getWeatherByCity(@RequestParam String city) {
        try{
            WeatherResponse weatherResponse = weatherService.getWeatherByCity(city);
            if (weatherResponse != null) {
                double feelsLike = weatherResponse.getMain().getFeelsLike() - 273.15;
                System.out.println("Feels Like : "+String.valueOf(feelsLike).substring(0,5)+"°C");
                return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("City Not Found",HttpStatus.NOT_FOUND);
        }catch(Exception e)
        {
            return new ResponseEntity<>("City Not Found",HttpStatus.NOT_FOUND);
        }

    }
}


