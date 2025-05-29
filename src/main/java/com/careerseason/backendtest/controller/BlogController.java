package com.careerseason.backendtest.controller;

import com.careerseason.backendtest.entity.Blog;
import com.careerseason.backendtest.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // For development; restrict in production
public class BlogController {
    
    private final BlogService blogService;
    
    @GetMapping
    public List<Blog> getAllBlogs(@RequestParam(required = false) Boolean visibleOnly) {
        return visibleOnly != null && visibleOnly
            ? blogService.getVisibleBlogs()
            : blogService.getAllBlogs();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable String id) {
        return blogService.getBlogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/textId/{textId}")
    public ResponseEntity<Blog> getBlogByTextId(@PathVariable String textId) {
        return blogService.getBlogByTextId(textId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/site/{site}")
    public List<Blog> getBlogsBySite(@PathVariable String site) {
        return blogService.getBlogsBySite(site);
    }
    
    @GetMapping("/filter")
    public List<Blog> filterBlogs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String site) {
        
        if (keyword != null && !keyword.isEmpty()) {
            return blogService.searchBlogs(keyword);
        } else if (author != null && !author.isEmpty()) {
            return blogService.getBlogsByAuthor(author);
        } else if (site != null && !site.isEmpty()) {
            return blogService.getBlogsBySite(site);
        } else {
            return blogService.getAllBlogs();
        }
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Blog createBlog(@RequestBody Blog blog) {
        return blogService.createBlog(blog);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable String id, @RequestBody Blog blog) {
        return blogService.updateBlog(id, blog)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable String id) {
        boolean deleted = blogService.deleteBlog(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
} 