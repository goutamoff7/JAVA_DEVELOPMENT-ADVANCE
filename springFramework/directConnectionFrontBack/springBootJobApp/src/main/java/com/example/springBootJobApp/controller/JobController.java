package com.example.springBootJobApp.controller;

import com.example.springBootJobApp.model.JobPost;
import com.example.springBootJobApp.service.JobService;
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

    @GetMapping(value = "jobPosts", produces = {"application/xml"})
    public List<JobPost> getAllJobsXML() {
        return jobService.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable Integer postId) {
        return jobService.getJob(postId);
    }

    @PostMapping("jobPost")
    public void addJob(@RequestBody JobPost jobPost) {
        jobService.addJob(jobPost);
    }

    @PostMapping(value = "jobPost", consumes = {"application/xml"})
    public void addJobXML(@RequestBody JobPost jobPost) {
        jobService.addJob(jobPost);
    }

    @PutMapping("jobPost")
    public void updateJob(@RequestBody JobPost jobPost) {
        jobService.updateJob(jobPost);
    }

    @DeleteMapping("jobPost/{postId}")
    public void deleteJob(@PathVariable Integer postId) {
        jobService.deleteJob(postId);
    }

    @GetMapping("jobPost/load")
    public String loadData() {
        jobService.loadData();
        return "Dummy Data Loaded";
    }

    @GetMapping("jobPost/search/{keyword}")
    public List<JobPost> search(@PathVariable String keyword) {
        return jobService.searchByKeyword(keyword);
    }
}

