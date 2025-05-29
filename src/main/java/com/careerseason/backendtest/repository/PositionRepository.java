package com.careerseason.backendtest.repository;

import com.careerseason.backendtest.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
    
    List<Position> findByHiddenFalse();
    
    List<Position> findByStatus(String status);
    
    List<Position> findBySite(String site);
    
    List<Position> findByJobType(String jobType);
    
    List<Position> findBySeason(String season);
    
    List<Position> findByVisaSponsored(String visaSponsored);
    
    List<Position> findByCompany(String company);
    
    @Query("SELECT p FROM Position p WHERE " +
           "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Position> search(String keyword);
} 