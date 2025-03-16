package com.goutam.weatherApp.cache;

import com.goutam.weatherApp.constants.Keys;
import com.goutam.weatherApp.model.ApiKeys;
import com.goutam.weatherApp.repository.ApiKeysRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ApiKeysRepository apiKeysRepository;

    public Map<String,String> appCacheMap;

   @PostConstruct
    public void init(){
       appCacheMap = new HashMap<>();
       ApiKeys apiKeys = apiKeysRepository.findByApiName(Keys.WEATHER_API.toString());
       appCacheMap.put(apiKeys.getApiName(),apiKeys.getApiUrl());
    }

}
