package com.careerseason.backendtest.controller;

import com.careerseason.backendtest.entity.Position;
import com.careerseason.backendtest.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // For development; restrict in production
public class PositionController {
    
    private final PositionService positionService;
    
    @GetMapping
    public List<Position> getAllPositions(
            @RequestParam(required = false) Boolean visibleOnly) {
        return visibleOnly != null && visibleOnly 
            ? positionService.getVisiblePositions() 
            : positionService.getAllPositions();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable String id) {
        return positionService.getPositionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Consolidated filter endpoint with query parameters
    @GetMapping("/filter")
    public List<Position> filterPositions(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String site,
            @RequestParam(required = false) String jobType,
            @RequestParam(required = false) String season,
            @RequestParam(required = false) String visaSponsored,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String keyword) {
        
        if (status != null) return positionService.getPositionsByStatus(status);
        if (site != null) return positionService.getPositionsBySite(site);
        if (jobType != null) return positionService.getPositionsByJobType(jobType);
        if (season != null) return positionService.getPositionsBySeason(season);
        if (visaSponsored != null) return positionService.getPositionsByVisaSponsorship(visaSponsored);
        if (company != null) return positionService.getPositionsByCompany(company);
        if (keyword != null) return positionService.searchPositions(keyword);
        
        return positionService.getAllPositions();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Position createPosition(@RequestBody Position position) {
        return positionService.createPosition(position);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Position> updatePosition(@PathVariable String id, @RequestBody Position position) {
        return positionService.updatePosition(id, position)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable String id) {
        boolean deleted = positionService.deletePosition(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
} 