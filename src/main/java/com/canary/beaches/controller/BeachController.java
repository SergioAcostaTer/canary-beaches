package com.canary.beaches.controller;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.model.Beach;
import com.canary.beaches.repository.BeachRepository;
import com.canary.beaches.service.BeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/beaches")
public class BeachController {

    private final BeachService beachService;
    private final BeachRepository beachRepository;


    @Autowired
    public BeachController(BeachService beachService, BeachRepository beachRepository) {
        this.beachService = beachService;
        this.beachRepository = beachRepository;
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
}
