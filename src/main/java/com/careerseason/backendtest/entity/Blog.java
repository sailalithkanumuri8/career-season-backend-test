package com.careerseason.backendtest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "blogs")
@Data
public class Blog {

    @Id
    @Column(columnDefinition = "uuid")
    private String id;

    private String title;
    
    @Column(name = "publishedat")
    private LocalDate publishedAt;
    
    @Column(name = "textid")
    private String textId;
    
    @Column(columnDefinition = "text")
    private String description;
    
    @Column(columnDefinition = "text")
    private String content;
    
    private String author;
    
    @Column(name = "authorimage", columnDefinition = "uuid")
    private String authorImage;
    
    @Column(name = "previewimage", columnDefinition = "uuid")
    private String previewImage;
    
    private String site;
    
    private Boolean hidden;
} 