package com.example.springBootJobApp.service;

import com.example.springBootJobApp.model.JobPost;
import com.example.springBootJobApp.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class JobService {

    private JobRepository jobRepository;

    public void addJob(JobPost jobPost) {
        jobRepository.save(jobPost);
    }

    public List<JobPost> getAllJobs() {
        return jobRepository.findAll();
    }

    public JobPost getJob(Integer postId) {
        return jobRepository.findById(postId).orElse(null);
    }

    public void updateJob(int postId,JobPost jobPost) {
        if(jobRepository.findById(postId).isPresent())
            jobRepository.save(jobPost);
    }

    public void deleteJob(Integer postId) {
        jobRepository.deleteById(postId);
    }

    public List<JobPost> searchByKeyword(String keyword){
        return jobRepository
                .findByPostProfileContainingIgnoreCaseOrPostDescriptionContainingIgnoreCase(
                        keyword,keyword);
    }

    public void loadData() {
        List<JobPost> jobs = new ArrayList<>(List.of(

                new JobPost(1,
                        "Java Developer",
                        "Must have good experience in core Java and advanced Java",
                        2,
                        List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),


                new JobPost(2,
                        "Frontend Developer",
                        "Experience in building responsive web applications using React",
                        3,
                        List.of("HTML", "CSS", "JavaScript", "React")),


                new JobPost(3,
                        "Data Scientist",
                        "Strong background in machine learning and data analysis",
                        4,
                        List.of("Python", "Machine Learning", "Data Analysis")),


                new JobPost(4,
                        "Network Engineer",
                        "Design and implement computer networks for efficient data communication",
                        5,
                        List.of("Networking", "Cisco", "Routing", "Switching")),


                new JobPost(5,
                        "Mobile App Developer",
                        "Experience in mobile app development for iOS and Android",
                        3,
                        List.of("iOS Development", "Android Development", "Mobile App"))
        ));
        jobRepository.saveAll(jobs);
    }

    public List<JobPost> searchByRequiredExperience(int requiredExperience) {
        return jobRepository.findByRequiredExperience(requiredExperience);
    }
}
