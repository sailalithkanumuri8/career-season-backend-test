package com.careerseason.backendtest.repository;

import com.careerseason.backendtest.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    List<Job> findByStatusEquals(String status);
    
    List<Job> findByJobTypeEquals(String jobType);
    
    List<Job> findByLocationContainingIgnoreCase(String location);
    
    List<Job> findByCompanyNameContainingIgnoreCase(String companyName);
    
    List<Job> findByTitleContainingIgnoreCase(String title);
} 