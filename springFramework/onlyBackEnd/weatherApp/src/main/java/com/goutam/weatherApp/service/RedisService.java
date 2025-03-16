package com.goutam.weatherApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goutam.weatherApp.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public <T> T get(String city, Class<T> entityClass){
        try{
            Object object = redisTemplate.opsForValue().get("weather_of_"+city.toLowerCase());
            return objectMapper.readValue(String.valueOf(object),entityClass);
        }catch(Exception e){
            log.error("Exception : "+e);
            return null;
        }
    }

    public void set(String city, WeatherResponse weatherResponse, Long expiry){
        try{
            String jsonValue = objectMapper.writeValueAsString(weatherResponse);
            redisTemplate.opsForValue().set("weather_of_"+city.toLowerCase(),jsonValue,expiry, TimeUnit.HOURS);
        }catch(Exception e){
            log.error("Exception : "+e);
        }
    }
}
