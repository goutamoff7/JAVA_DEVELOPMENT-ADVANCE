package com.example.springBootJobApp.repository;

import com.example.springBootJobApp.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobPost,Integer> {

    List<JobPost> findByPostProfileContainingIgnoreCaseOrPostDescriptionContainingIgnoreCase(
            String postProfile, String postDescription);

    List<JobPost> findByRequiredExperience(int requiredExperience);
}

