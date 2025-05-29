package com.careerseason.backendtest.service;

import com.careerseason.backendtest.entity.Blog;
import com.careerseason.backendtest.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlogService {
    
    private final BlogRepository blogRepository;
    
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
    
    public List<Blog> getVisibleBlogs() {
        return blogRepository.findByHiddenFalse();
    }
    
    public Optional<Blog> getBlogById(String id) {
        return blogRepository.findById(id);
    }
    
    public Optional<Blog> getBlogByTextId(String textId) {
        return blogRepository.findByTextId(textId);
    }
    
    public List<Blog> searchBlogs(String keyword) {
        return blogRepository.search(keyword);
    }
    
    public List<Blog> getBlogsByAuthor(String author) {
        return blogRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    public List<Blog> getBlogsBySite(String site) {
        return blogRepository.findBySite(site);
    }
    
    public Blog createBlog(Blog blog) {
        if (blog.getId() == null || blog.getId().isEmpty()) {
            blog.setId(UUID.randomUUID().toString());
        }
        if (blog.getPublishedAt() == null) {
            blog.setPublishedAt(LocalDate.now());
        }
        if (blog.getHidden() == null) {
            blog.setHidden(false);
        }
        return blogRepository.save(blog);
    }
    
    public Optional<Blog> updateBlog(String id, Blog blogDetails) {
        return blogRepository.findById(id)
            .map(existingBlog -> {
                updateNonNullFields(existingBlog, blogDetails);
                return blogRepository.save(existingBlog);
            });
    }
    
    private void updateNonNullFields(Blog existingBlog, Blog updates) {
        if (updates.getTitle() != null) existingBlog.setTitle(updates.getTitle());
        if (updates.getTextId() != null) existingBlog.setTextId(updates.getTextId());
        if (updates.getDescription() != null) existingBlog.setDescription(updates.getDescription());
        if (updates.getContent() != null) existingBlog.setContent(updates.getContent());
        if (updates.getAuthor() != null) existingBlog.setAuthor(updates.getAuthor());
        if (updates.getAuthorImage() != null) existingBlog.setAuthorImage(updates.getAuthorImage());
        if (updates.getPreviewImage() != null) existingBlog.setPreviewImage(updates.getPreviewImage());
        if (updates.getSite() != null) existingBlog.setSite(updates.getSite());
        if (updates.getHidden() != null) existingBlog.setHidden(updates.getHidden());
        if (updates.getPublishedAt() != null) existingBlog.setPublishedAt(updates.getPublishedAt());
    }
    
    public boolean deleteBlog(String id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }
} 