package com.example.springDataRestDemo.repository;


import com.example.springDataRestDemo.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobPost,Integer> {

    List<JobPost> findByPostProfileContainingIgnoreCaseOrPostDescriptionContainingIgnoreCase(
            String postProfile, String postDescription);
}

