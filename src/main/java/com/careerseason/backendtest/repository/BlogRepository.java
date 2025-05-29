package com.careerseason.backendtest.repository;

import com.careerseason.backendtest.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {
    
    List<Blog> findByHiddenFalse();
    
    Optional<Blog> findByTextId(String textId);
    
    List<Blog> findBySite(String site);
    
    @Query("SELECT b FROM Blog b WHERE " +
           "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(b.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Blog> search(String keyword);
    
    List<Blog> findByAuthorContainingIgnoreCase(String author);
} 