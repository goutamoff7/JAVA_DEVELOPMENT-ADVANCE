package com.goutam.weatherApp.service;

import com.goutam.weatherApp.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private  String apiKey ;
    @Value("${weather.api}")
    private  String API ;

    RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeatherByCity(String city) throws Exception{
        String finalAPI = API.replace("API_KEY", apiKey).replace("CITY", city);
        ResponseEntity<WeatherResponse> response = restTemplate.
                exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();

    }
}
