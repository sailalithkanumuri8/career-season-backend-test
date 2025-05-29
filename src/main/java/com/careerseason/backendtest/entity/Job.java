package com.careerseason.backendtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Column(name = "company_name")
    private String companyName;
    
    @Column(name = "company_logo")
    private String companyLogo;
    
    private String location;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "job_type")
    private String jobType;
    
    private String salary;
    
    @Column(name = "application_link")
    private String applicationLink;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    private String status;
    
    @Column(name = "posted_by")
    private String postedBy;
} 