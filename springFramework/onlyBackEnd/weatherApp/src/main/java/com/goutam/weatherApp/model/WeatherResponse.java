package com.goutam.weatherApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
@Data
@NoArgsConstructor
public class WeatherResponse {
    private ArrayList<Weather> weather;
    private Main main;
    private String name;

    @Component
    @Data
    public static class Main {
        @JsonProperty("temp")
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
        @JsonProperty("temp_min")
        private double tempMin;
        @JsonProperty("temp_max")
        private double tempMax;
        @JsonProperty("pressure")
        private int pressure;
        @JsonProperty("humidity")
        private int humidity;

    }

    @Component
    @Data
    public static class Weather {
        @JsonProperty("description")
        private String description;

    }
}


