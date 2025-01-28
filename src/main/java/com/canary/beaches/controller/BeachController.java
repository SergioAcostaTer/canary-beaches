package com.canary.beaches.controller;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.model.Beach;
import com.canary.beaches.repository.BeachRepository;
import com.canary.beaches.service.BeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/beaches")
public class BeachController {

    private final BeachService beachService;


    @Autowired
    public BeachController(BeachService beachService, BeachRepository beachRepository) {
        this.beachService = beachService;
    }

    @GetMapping
    public ResponseEntity<List<Beach>> findAll() {
        List<Beach> beaches = beachService.findAll();
        return ResponseEntity.ok(beaches);
    }

    @GetMapping("/{id}")
    public Optional<BeachDto> getBeach(@PathVariable Long id) {
        return beachService.findById(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BeachDto>> searchBeaches(
            @RequestParam(required = false) String query,
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        return ResponseEntity.ok(beachService.searchBeaches(query, pageable));
    }
}
