package com.learnwithtiwari.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnwithtiwari.userservice.request.JobCreationRequest;
import com.learnwithtiwari.userservice.request.UpdateJobRequest;
import com.learnwithtiwari.userservice.service.JobsService;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobsService jobsService;

    @GetMapping("/fetchList")
    public ResponseEntity<Object> getAllJobs(){
        return jobsService.getAllJobs();
    }


    @PostMapping("/create")
    public ResponseEntity<String> createJobs(@RequestBody JobCreationRequest jobInput){
        return jobsService.createJobs(jobInput);
    }

    @PutMapping("/update/{jobId}")
    public ResponseEntity<String> updateJobs(@PathVariable Long jobId, @RequestBody UpdateJobRequest updateJob){
        return jobsService.updateJobs(jobId,updateJob);
    }

    @GetMapping("/get/{jobId}")
    public ResponseEntity<Object> getJobById(@PathVariable Long jobId){
        return jobsService.getJobById(jobId);
    }


    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<Object> deleteJob(@PathVariable Long jobId){
        return jobsService.deleteJob(jobId);
    }

}
