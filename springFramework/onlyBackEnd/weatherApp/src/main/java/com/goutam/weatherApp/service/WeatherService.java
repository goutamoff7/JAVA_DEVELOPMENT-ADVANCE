package com.goutam.weatherApp.service;

import com.goutam.weatherApp.model.WeatherResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private static final String apiKey = "361f21f005338ba7bea388ea85bd83b7";
    private static final String API = "http://api.openweathermap.org/data/2.5/weather?APPID=API_KEY&q=CITY";

    RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeatherByCity(String city) throws Exception{
        String finalAPI = API.replace("API_KEY", apiKey).replace("CITY", city);
        ResponseEntity<WeatherResponse> response = restTemplate.
                exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();

    }
}
