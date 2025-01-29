package com.canary.beaches.service.impl;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.dto.BeachPreviewDto;
import com.canary.beaches.dto.PaginatedResponse;
import com.canary.beaches.mapper.BeachMapper;
import com.canary.beaches.model.Beach;
import com.canary.beaches.model.enums.Island;
import com.canary.beaches.repository.BeachRepository;
import com.canary.beaches.service.BeachService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class BeachServiceImpl implements BeachService {

    private final BeachRepository beachRepository;

    @Autowired
    public BeachServiceImpl(BeachRepository beachRepository) {
        this.beachRepository = beachRepository;
    }

    @Override
    public List<Beach> findAll() {
        return beachRepository.findAll();
    }

    @Override
    public Optional<BeachDto> findById(Long id) {
        Optional<Beach> beach = beachRepository.findById(id);
        return beach.map(BeachMapper::toDto);
    }

    @Override
    public Beach save(Beach beach) {
        return beachRepository.save(beach);
    }


    @Override
    public PaginatedResponse<BeachDto> searchBeaches(String query, Pageable pageable) {
        String formattedQuery = query.toLowerCase();
        Specification<Beach> spec = (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(formattedQuery)) {
                String likeQuery = "%" + formattedQuery + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("name")), likeQuery),
                        cb.like(cb.lower(root.get("municipality")), likeQuery),
                        cb.like(cb.lower(root.get("province")), likeQuery)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<BeachDto> page = beachRepository.findAll(spec, pageable)
                .map(BeachMapper::toDto);

        return new PaginatedResponse<>(page);
    }

    @Override
    public PaginatedResponse<BeachPreviewDto> searchBeachesPreview(String query, Pageable pageable) {
        Specification<Beach> spec = (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(query)) {
                String likeQuery = "%" + query + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("name")), likeQuery),
                        cb.like(cb.lower(root.get("municipality")), likeQuery),
                        cb.like(cb.lower(root.get("province")), likeQuery)
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<BeachPreviewDto> page = beachRepository.findAll(spec, pageable)
                .map(BeachMapper::toPreviewDto);

        return new PaginatedResponse<>(page);
    }


    @Override
    public Optional<BeachDto> getRandomBeach(Island island) {
        String islandName = island == null ? null : island.name();
        Beach beach = beachRepository.findRandomByIsland(islandName)
                .orElseThrow(() -> new IllegalArgumentException("No beaches found for island " + islandName));
        return Optional.of(BeachMapper.toDto(beach));
    }

    @Override
    public PaginatedResponse<BeachPreviewDto> getNearbyBeaches(Double latitude, Double longitude, Double radius, Pageable pageable) {

        Page<BeachPreviewDto> page = beachRepository.findNearbyBeaches(latitude, longitude, radius / 1000 , pageable)
                .map(beach -> {
                    BeachPreviewDto dto = BeachMapper.toPreviewDto(beach);
                    return dto.calculateDistance(latitude, longitude);
                });

        return new PaginatedResponse<>(page);
    }


}