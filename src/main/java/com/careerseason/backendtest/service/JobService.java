package com.careerseason.backendtest.service;

import com.careerseason.backendtest.entity.Job;
import com.careerseason.backendtest.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {
    
    private final JobRepository jobRepository;
    
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    
    public List<Job> getActiveJobs() {
        return jobRepository.findByStatusEquals("active");
    }
    
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }
    
    public List<Job> getJobsByType(String jobType) {
        return jobRepository.findByJobTypeEquals(jobType);
    }
    
    public List<Job> getJobsByLocation(String location) {
        return jobRepository.findByLocationContainingIgnoreCase(location);
    }
    
    public List<Job> getJobsByCompany(String companyName) {
        return jobRepository.findByCompanyNameContainingIgnoreCase(companyName);
    }
    
    public List<Job> searchJobsByTitle(String title) {
        return jobRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public Job createJob(Job job) {
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());
        if (job.getStatus() == null) {
            job.setStatus("pending");
        }
        return jobRepository.save(job);
    }
    
    public Optional<Job> updateJob(Long id, Job jobDetails) {
        return jobRepository.findById(id).map(existingJob -> {
            if (jobDetails.getTitle() != null) {
                existingJob.setTitle(jobDetails.getTitle());
            }
            if (jobDetails.getCompanyName() != null) {
                existingJob.setCompanyName(jobDetails.getCompanyName());
            }
            if (jobDetails.getCompanyLogo() != null) {
                existingJob.setCompanyLogo(jobDetails.getCompanyLogo());
            }
            if (jobDetails.getLocation() != null) {
                existingJob.setLocation(jobDetails.getLocation());
            }
            if (jobDetails.getDescription() != null) {
                existingJob.setDescription(jobDetails.getDescription());
            }
            if (jobDetails.getJobType() != null) {
                existingJob.setJobType(jobDetails.getJobType());
            }
            if (jobDetails.getSalary() != null) {
                existingJob.setSalary(jobDetails.getSalary());
            }
            if (jobDetails.getApplicationLink() != null) {
                existingJob.setApplicationLink(jobDetails.getApplicationLink());
            }
            if (jobDetails.getStatus() != null) {
                existingJob.setStatus(jobDetails.getStatus());
            }
            existingJob.setUpdatedAt(LocalDateTime.now());
            return jobRepository.save(existingJob);
        });
    }
    
    public boolean deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
} 