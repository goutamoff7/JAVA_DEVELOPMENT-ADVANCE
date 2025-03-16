package com.goutam.weatherApp.service;

import com.goutam.weatherApp.cache.AppCache;
import com.goutam.weatherApp.constants.Keys;
import com.goutam.weatherApp.constants.Placeholder;
import com.goutam.weatherApp.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private AppCache appCache;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RedisService redisService;

    RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeatherByCity(String city) throws Exception {
        WeatherResponse cachedWeatherResponse = redisService.get(city, WeatherResponse.class);
        if (cachedWeatherResponse != null)
            return cachedWeatherResponse;
        else {
            String finalAPI = appCache
                    .appCacheMap
                    .get(Keys.WEATHER_API.toString())
                    .replace(Placeholder.API_KEY, apiKey).replace(Placeholder.CITY, city);

            ResponseEntity<WeatherResponse> response = restTemplate.
                    exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            log.info(String.valueOf(response));
            WeatherResponse body = response.getBody();
            if(body!=null)
                redisService.set(city,body,1L);
            return body;
        }
    }
}
