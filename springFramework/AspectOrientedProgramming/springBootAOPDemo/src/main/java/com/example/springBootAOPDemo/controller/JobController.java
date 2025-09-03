package com.example.springBootAOPDemo.controller;

import com.example.springBootAOPDemo.model.JobPost;
import com.example.springBootAOPDemo.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class JobController {

    private JobService jobService;

    @GetMapping("jobPosts")
    public List<JobPost> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("jobPosts/{postId}")
    public JobPost getJob(@PathVariable Integer postId) {
        return jobService.getJob(postId);
    }

    @PostMapping("jobPosts")
    public void addJob(@RequestBody JobPost jobPost) {
        jobService.addJob(jobPost);
    }

    @PutMapping("jobPosts/{postId}")
    public void updateJob(@PathVariable int postId, @RequestBody JobPost jobPost) {
        jobService.updateJob(postId,jobPost);
    }

    @DeleteMapping("jobPosts/{postId}")
    public void deleteJob(@PathVariable Integer postId) {
        jobService.deleteJob(postId);
    }

    @GetMapping("jobPosts/load")
    public String loadData() {
        jobService.loadData();
        return "Dummy Data Loaded";
    }

    @GetMapping("jobPosts/search")
    public List<JobPost> search(@RequestParam String keyword) {
        return jobService.searchByKeyword(keyword);
    }

    @GetMapping("jobPosts/searchByRequiredExperience")
    public List<JobPost> search(@RequestParam int requiredExperience) {
        return jobService.searchByRequiredExperience(requiredExperience);
    }
}

