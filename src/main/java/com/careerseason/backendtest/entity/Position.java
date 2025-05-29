package com.careerseason.backendtest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "positions")
@Data
public class Position {

    @Id
    @Column(columnDefinition = "uuid")
    private String id;

    @Column(columnDefinition = "text")
    private String title;
    private String status;
    
    @Column(columnDefinition = "text")
    private String url;
    
    @Column(columnDefinition = "text")
    private String description;
    
    private String season;
    private Boolean hidden;
    
    @Column(name = "salarytext", columnDefinition = "text")
    private String salaryText;
    
    @Column(name = "visasponsored")
    private String visaSponsored;
    
    @Column(name = "lastupdated")
    private LocalDateTime lastUpdated;
    
    @Column(name = "createdat")
    private LocalDateTime createdAt;
    
    @Column(name = "dateadded")
    private LocalDateTime dateAdded;
    
    @Column(columnDefinition = "uuid")
    private String company;
    
    private String site;
    
    @Column(name = "jobtype")
    private String jobType;
    
    @Column(name = "directus_roles")
    private String directusRoles;
} 