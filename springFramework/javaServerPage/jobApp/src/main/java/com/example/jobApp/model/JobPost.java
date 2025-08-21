package com.example.jobApp.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class JobPost {

    private int postId;
    private String postProfile;
    private String postDescription;
    private int requiredExperience;
    private List<String> postTechStack;
}
