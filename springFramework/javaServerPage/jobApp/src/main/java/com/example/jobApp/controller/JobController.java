package com.example.jobApp.controller;

import com.example.jobApp.model.JobPost;
import com.example.jobApp.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class JobController {

    private JobService jobService;

    @RequestMapping({"/", "home"})
    public String home() {
        return "home";
    }

    @RequestMapping("add-job")
    public String addJob() {
        return "addJob";
    }

    @PostMapping("handle-form")
    public String handleForm(JobPost jobPost) {
        jobService.addJob(jobPost);
        return "success";
    }

    @RequestMapping("view-all-jobs")
    public String viewAllJob(Model model) {
        List<JobPost> jobPosts = jobService.getAllJobs();
        model.addAttribute("jobPosts",jobPosts);
        return "viewAllJobs";
    }


}

