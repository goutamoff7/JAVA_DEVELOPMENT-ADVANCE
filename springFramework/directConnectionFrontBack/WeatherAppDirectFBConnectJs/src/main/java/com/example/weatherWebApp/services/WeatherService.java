package com.example.weatherWebApp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    public Map<String, Object> getWeatherInfo(String cityName) {
        try {
            RestTemplate template = new RestTemplate();
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?APPID=361f21f005338ba7bea388ea85bd83b7&q=";
            ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + cityName, ObjectNode.class);
            ObjectNode jsonObject = response.getBody();

            if (jsonObject != null) {
                Map<String, Object> weatherData = new HashMap<>();

                // Extract name
                String name = jsonObject.path("name").asText();
                weatherData.put("cityName",name);

                // Extract temperature
                int temperature = jsonObject.path("main").path("temp").asInt();
                weatherData.put("temperature",temperature-273); // converting kelvin to Degree Celsius

                // Extract feels_like
                int feelsLike = jsonObject.path("main").path("feels_like").asInt();
                weatherData.put("feelsLike",feelsLike-273); // converting kelvin to Degree Celsius

                // Extract humidity
                int humidity = jsonObject.path("main").path("humidity").asInt();
                weatherData.put("humidity", humidity);

                // Extract wind speed
                double windSpeed = jsonObject.path("wind").path("speed").asDouble();
                weatherData.put("windSpeed", windSpeed);

                // Extract weather description
                String description = jsonObject.path("weather").get(0).path("description").asText();
                weatherData.put("description", description);

                System.out.println(weatherData);
                return weatherData;
            } else {
                throw new Exception("JSON response is null");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
