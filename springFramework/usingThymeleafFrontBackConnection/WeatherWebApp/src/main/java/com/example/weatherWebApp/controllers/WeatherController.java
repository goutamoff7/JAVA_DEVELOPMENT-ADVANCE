package com.example.weatherWebApp.controllers;

import com.example.weatherWebApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView submitForm(@RequestParam(required = false) String cityName) {
        if(cityName==null)
        {
            String viewName = "searchWeather";
            return new ModelAndView(viewName);
        }
        else
        {
            String viewName = "view";
            Map<String, Object> weatherData = weatherService.getWeatherInfo(cityName);

            if (weatherData != null) {
                weatherData.put("cityName", cityName);
                weatherData.put("temperature", weatherData.get("temperature"));
                weatherData.put("feelsLike",weatherData.get("feelsLike"));
                weatherData.put("humidity", weatherData.get("humidity"));
                weatherData.put("windSpeed", weatherData.get("windSpeed"));
                weatherData.put("description", weatherData.get("description"));
            } else {
                weatherData = new HashMap<>();
                weatherData.put("cityName", cityName);
                weatherData.put("temperature", "N/A");
                weatherData.put("feelsLike","N/A");
                weatherData.put("humidity", "N/A");
                weatherData.put("windSpeed", "N/A");
                weatherData.put("description", "N/A");
            }
            return new ModelAndView(viewName, weatherData);
        }


    }


}
