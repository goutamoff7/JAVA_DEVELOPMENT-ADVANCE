package com.goutam.weatherApp.repository;

import com.goutam.weatherApp.model.ApiKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeysRepository extends JpaRepository<ApiKeys,Integer> {

    ApiKeys findByApiName(String apiName);
}
