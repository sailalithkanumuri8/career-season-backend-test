package com.careerseason.backendtest.service;

import com.careerseason.backendtest.entity.Position;
import com.careerseason.backendtest.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositionService {
    
    private final PositionRepository positionRepository;
    
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }
    
    public List<Position> getVisiblePositions() {
        return positionRepository.findByHiddenFalse();
    }
    
    public Optional<Position> getPositionById(String id) {
        return positionRepository.findById(id);
    }
    
    public List<Position> getPositionsByStatus(String status) {
        return positionRepository.findByStatus(status);
    }
    
    public List<Position> getPositionsBySite(String site) {
        return positionRepository.findBySite(site);
    }
    
    public List<Position> getPositionsByJobType(String jobType) {
        return positionRepository.findByJobType(jobType);
    }
    
    public List<Position> getPositionsBySeason(String season) {
        return positionRepository.findBySeason(season);
    }
    
    public List<Position> getPositionsByVisaSponsorship(String visaSponsored) {
        return positionRepository.findByVisaSponsored(visaSponsored);
    }
    
    public List<Position> getPositionsByCompany(String company) {
        return positionRepository.findByCompany(company);
    }
    
    public List<Position> searchPositions(String keyword) {
        return positionRepository.search(keyword);
    }
    
    public Position createPosition(Position position) {
        LocalDateTime now = LocalDateTime.now();
        
        if (position.getId() == null || position.getId().isEmpty()) {
            position.setId(UUID.randomUUID().toString());
        }
        if (position.getCreatedAt() == null) {
            position.setCreatedAt(now);
        }
        if (position.getLastUpdated() == null) {
            position.setLastUpdated(now);
        }
        if (position.getDateAdded() == null) {
            position.setDateAdded(now);
        }
        if (position.getHidden() == null) {
            position.setHidden(false);
        }
        
        return positionRepository.save(position);
    }
    
    public Optional<Position> updatePosition(String id, Position positionDetails) {
        return positionRepository.findById(id)
            .map(existingPosition -> {
                updateNonNullFields(existingPosition, positionDetails);
                existingPosition.setLastUpdated(LocalDateTime.now());
                return positionRepository.save(existingPosition);
            });
    }
    
    private void updateNonNullFields(Position existingPosition, Position updates) {
        if (updates.getTitle() != null) existingPosition.setTitle(updates.getTitle());
        if (updates.getStatus() != null) existingPosition.setStatus(updates.getStatus());
        if (updates.getUrl() != null) existingPosition.setUrl(updates.getUrl());
        if (updates.getDescription() != null) existingPosition.setDescription(updates.getDescription());
        if (updates.getSeason() != null) existingPosition.setSeason(updates.getSeason());
        if (updates.getHidden() != null) existingPosition.setHidden(updates.getHidden());
        if (updates.getSalaryText() != null) existingPosition.setSalaryText(updates.getSalaryText());
        if (updates.getVisaSponsored() != null) existingPosition.setVisaSponsored(updates.getVisaSponsored());
        if (updates.getCompany() != null) existingPosition.setCompany(updates.getCompany());
        if (updates.getSite() != null) existingPosition.setSite(updates.getSite());
        if (updates.getJobType() != null) existingPosition.setJobType(updates.getJobType());
        if (updates.getDirectusRoles() != null) existingPosition.setDirectusRoles(updates.getDirectusRoles());
    }
    
    public boolean deletePosition(String id) {
        if (positionRepository.existsById(id)) {
            positionRepository.deleteById(id);
            return true;
        }
        return false;
    }
} 