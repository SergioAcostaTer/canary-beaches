package com.canary.beaches.service;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.dto.BeachPreviewDto;
import com.canary.beaches.dto.PaginatedResponse;
import com.canary.beaches.model.Beach;
import com.canary.beaches.model.enums.Island;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BeachService {
    List<Beach> findAll();
    Optional<BeachDto> findById(Long id);
    Beach save(Beach beach);
    PaginatedResponse<BeachDto> searchBeaches(String query, Pageable pageable);
    PaginatedResponse<BeachPreviewDto> searchBeachesPreview(String query, Pageable pageable);
    Optional<BeachDto> getRandomBeach(Island island);
    PaginatedResponse<BeachPreviewDto> getNearbyBeaches(Double latitude, Double longitude, Double radius, Pageable pageable);
}