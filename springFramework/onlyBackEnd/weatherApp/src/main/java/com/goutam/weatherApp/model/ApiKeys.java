package com.goutam.weatherApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeys {

    @Id
    private Integer id;
    private String apiName;
    private String apiUrl;

}
