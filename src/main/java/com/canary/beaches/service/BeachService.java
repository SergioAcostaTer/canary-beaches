package com.canary.beaches.service;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.dto.BeachPreviewDto;
import com.canary.beaches.model.Beach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BeachService {
    List<Beach> findAll();
    Optional<BeachDto> findById(Long id);
    Beach save(Beach beach);
    Page<BeachDto> searchBeaches(String query, Pageable pageable);
    Page<BeachPreviewDto> searchBeachesPreview(String query, Pageable pageable);
}