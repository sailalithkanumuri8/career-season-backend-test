package com.careerseason.backendtest.controller;

import com.careerseason.backendtest.entity.Job;
import com.careerseason.backendtest.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // For development; restrict in production
public class JobController {
    
    private final JobService jobService;
    
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(
            @RequestParam(required = false) Boolean activeOnly) {
        List<Job> jobs;
        if (activeOnly != null && activeOnly) {
            jobs = jobService.getActiveJobs();
        } else {
            jobs = jobService.getAllJobs();
        }
        return ResponseEntity.ok(jobs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/type/{jobType}")
    public ResponseEntity<List<Job>> getJobsByType(@PathVariable String jobType) {
        return ResponseEntity.ok(jobService.getJobsByType(jobType));
    }
    
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Job>> getJobsByLocation(@PathVariable String location) {
        return ResponseEntity.ok(jobService.getJobsByLocation(location));
    }
    
    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<Job>> getJobsByCompany(@PathVariable String companyName) {
        return ResponseEntity.ok(jobService.getJobsByCompany(companyName));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam String title) {
        return ResponseEntity.ok(jobService.searchJobsByTitle(title));
    }
    
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        if (jobService.deleteJob(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
} 